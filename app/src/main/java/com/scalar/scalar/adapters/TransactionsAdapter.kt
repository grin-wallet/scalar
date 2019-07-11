package com.scalar.scalar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scalar.scalar.R
import com.scalar.scalar.models.TransactionModel
import kotlinx.android.synthetic.main.item_transaction.view.*


class TransactionsAdapter(val mTransactions : List<TransactionModel>, val mContext : Context):
        RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_transaction,
                parent, false))
    }

    override fun getItemCount(): Int {
        return mTransactions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val txn = mTransactions[position]
        holder.transactionTypeTextView.text = txn.type.value
        holder.dateTextView.text = txn.createdAt
        holder.amountTextView.text = txn.amount
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var transactionTypeTextView : TextView = itemView.transactionTypeTextView
        var dateTextView : TextView = itemView.dateTextView
        var amountTextView : TextView = itemView.amountTextView
    }
}