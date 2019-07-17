package com.scalar.scalar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scalar.scalar.R
import com.scalar.scalar.models.TxnListViewModel
import kotlinx.android.synthetic.main.item_transaction.view.*
import kotlinx.android.synthetic.main.item_txn_list_header.view.*


class TransactionsAdapter(val items : ArrayList<Any>, val mContext : Context):
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ListViewType(val value :Int) {
        TRANSACTION_ITEM(0), HEADER(1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType) {
            ListViewType.TRANSACTION_ITEM.value -> {
                val v = inflater.inflate(R.layout.item_transaction, parent, false)
                TransactionsViewHolder(v)
            }
            ListViewType.HEADER.value -> {
                val v = inflater.inflate(R.layout.item_txn_list_header, parent, false)
                HeaderViewHolder(v)
            }
            else -> {
                val v = inflater.inflate(R.layout.item_transaction, parent, false)
                TransactionsViewHolder(v)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        items.let{
            return when {
                it[position] is TxnListViewModel -> ListViewType.TRANSACTION_ITEM.value
                it[position] is String -> ListViewType.HEADER.value
                else -> 0
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            ListViewType.TRANSACTION_ITEM.value -> {
                val viewHolder = holder as TransactionsViewHolder

                val txn = items[position] as TxnListViewModel
                viewHolder.transactionTypeTextView.text = txn.type.value
                viewHolder.dateTextView.text = txn.createdAt
                viewHolder.amountTextView.text = txn.amount
            }

            ListViewType.HEADER.value -> {
                val viewHolder = holder as HeaderViewHolder

                viewHolder.titleTextView.text = items[position] as String
            }
        }


    }

    inner class TransactionsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var transactionTypeTextView : TextView = itemView.transactionTypeTextView
        var dateTextView : TextView = itemView.dateTextView
        var amountTextView : TextView = itemView.amountTextView
    }

    inner class HeaderViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView : TextView = itemView.titleTextView
    }
}