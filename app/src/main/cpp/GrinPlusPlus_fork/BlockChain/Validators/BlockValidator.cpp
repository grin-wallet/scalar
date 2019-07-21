#include "BlockValidator.h"
#include "../Processors/BlockHeaderProcessor.h"

#include <Crypto/Crypto.h>
#include <Core/Validation/TransactionBodyValidator.h>
#include <Core/Validation/KernelSumValidator.h>
#include <Consensus/Common.h>
#include <PMMR/TxHashSet.h>
#include <algorithm>

BlockValidator::BlockValidator(const IBlockDB& blockDB, const ITxHashSet* pTxHashSet)
	: m_blockDB(blockDB), m_pTxHashSet(pTxHashSet)
{

}

// Validates a block is self-consistent and validates the state (eg. MMRs).
std::unique_ptr<BlockSums> BlockValidator::ValidateBlock(const FullBlock& block) const
{
	if (!IsBlockSelfConsistent(block))
	{
		return std::unique_ptr<BlockSums>(nullptr);
	}

	// Verify coinbase maturity
	const uint64_t maximumBlockHeight = (std::max)(block.GetBlockHeader().GetHeight() + 1, Consensus::COINBASE_MATURITY) - Consensus::COINBASE_MATURITY;
	for (const TransactionInput& input : block.GetTransactionBody().GetInputs())
	{
		if (input.GetFeatures() == EOutputFeatures::COINBASE_OUTPUT)
		{
			const std::optional<OutputLocation> outputPosOpt = m_blockDB.GetOutputPosition(input.GetCommitment());
			if (!outputPosOpt.has_value() || outputPosOpt.value().GetBlockHeight() > maximumBlockHeight)
			{
				LoggerAPI::LogInfo("BlockValidator::ValidateBlock - Coinbase not mature: " + HexUtil::ConvertHash(block.GetHash()));
				return std::unique_ptr<BlockSums>(nullptr);
			}
		}
	}

	if (!m_pTxHashSet->ValidateRoots(block.GetBlockHeader()))
	{
		return std::unique_ptr<BlockSums>(nullptr);
	}

	const Hash previousHash = block.GetBlockHeader().GetPreviousBlockHash();
	std::unique_ptr<BlockSums> pPreviousBlockSums = m_blockDB.GetBlockSums(previousHash);
	if (pPreviousBlockSums == nullptr)
	{
		LoggerAPI::LogWarning("BlockValidator::IsBlockValid - Failed to retrieve block sums for block " + HexUtil::ConvertHash(previousHash));
		return std::unique_ptr<BlockSums>(nullptr);
	}

	return KernelSumValidator::ValidateKernelSums(block.GetTransactionBody(), 0 - Consensus::REWARD, block.GetBlockHeader().GetTotalKernelOffset(), std::make_optional<BlockSums>(*pPreviousBlockSums));
}

// Validates all the elements in a block that can be checked without additional data. 
// Includes commitment sums and kernels, reward, etc.
bool BlockValidator::IsBlockSelfConsistent(const FullBlock& block) const
{
	if (block.WasValidated())
	{
		LoggerAPI::LogTrace("BlockValidator::IsBlockSelfConsistent - Block already validated: " + HexUtil::ConvertHash(block.GetHash()));
		return true;
	}

	if (!TransactionBodyValidator().ValidateTransactionBody(block.GetTransactionBody(), true))
	{
		LoggerAPI::LogError("BlockValidator::IsBlockSelfConsistent - Failed to validate transaction body for " + HexUtil::ConvertHash(block.GetHash()));
		return false;
	}

	if (!VerifyKernelLockHeights(block))
	{
		LoggerAPI::LogError("BlockValidator::IsBlockSelfConsistent - Failed to validate kernel lock heights for " + HexUtil::ConvertHash(block.GetHash()));
		return false;
	}

	if (!VerifyCoinbase(block))
	{
		LoggerAPI::LogError("BlockValidator::IsBlockSelfConsistent - Failed to validate coinbase for " + HexUtil::ConvertHash(block.GetHash()));
		return false;
	}

	block.MarkAsValidated();
	return true;
}

// check we have no kernels with lock_heights greater than current height
// no tx can be included in a block earlier than its lock_height
bool BlockValidator::VerifyKernelLockHeights(const FullBlock& block) const
{
	const uint64_t height = block.GetBlockHeader().GetHeight();
	for (const TransactionKernel& kernel : block.GetTransactionBody().GetKernels())
	{
		if (kernel.GetLockHeight() > height)
		{
			return false;
		}
	}

	return true;
}

// Validate the coinbase outputs generated by miners.
// Check the sum of coinbase-marked outputs match the sum of coinbase-marked kernels accounting for fees.
bool BlockValidator::VerifyCoinbase(const FullBlock& block) const
{
	std::vector<Commitment> coinbaseCommitments;
	for (const TransactionOutput& output : block.GetTransactionBody().GetOutputs())
	{
		if ((output.GetFeatures() & EOutputFeatures::COINBASE_OUTPUT) == EOutputFeatures::COINBASE_OUTPUT)
		{
			coinbaseCommitments.push_back(output.GetCommitment());
		}
	}

	std::vector<Commitment> coinbaseKernelExcesses;
	for (const TransactionKernel& kernel : block.GetTransactionBody().GetKernels())
	{
		if ((kernel.GetFeatures() & EKernelFeatures::COINBASE_KERNEL) == EKernelFeatures::COINBASE_KERNEL)
		{
			coinbaseKernelExcesses.push_back(kernel.GetExcessCommitment());
		}
	}

	uint64_t reward = Consensus::REWARD;
	for (const TransactionKernel& kernel : block.GetTransactionBody().GetKernels())
	{
		reward += kernel.GetFee();
	}

	std::unique_ptr<Commitment> pRewardCommitment = Crypto::CommitTransparent(reward);
	if (pRewardCommitment == nullptr)
	{
		return false;
	}

	const std::vector<Commitment> overCommitment({ *pRewardCommitment });
	const std::unique_ptr<Commitment> pOutputAdjustedSum = Crypto::AddCommitments(coinbaseCommitments, overCommitment);

	const std::unique_ptr<Commitment> pKernelSum = Crypto::AddCommitments(coinbaseKernelExcesses, std::vector<Commitment>());

	// Verify the kernel sum equals the output sum accounting for block fees.
	if (pOutputAdjustedSum == nullptr || pKernelSum == nullptr)
	{
		return false;
	}

	return *pKernelSum == *pOutputAdjustedSum;
}