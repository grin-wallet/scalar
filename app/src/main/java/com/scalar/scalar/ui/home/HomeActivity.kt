package com.scalar.scalar.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.scalar.scalar.ui.activities.BaseActivity
import com.scalar.scalar.R
import com.scalar.scalar.adapters.TransactionsAdapter
import com.scalar.scalar.models.TxnListViewModel
import com.scalar.scalar.models.TransactionType
import com.scalar.scalar.ui.receive.ReceiveActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    lateinit var mTransactions : ArrayList<Any>

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupUI()
    }

    private fun setupUI() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        transactionsRecyclerView.layoutManager = LinearLayoutManager(this)
        transactionsRecyclerView.adapter = TransactionsAdapter(getTransactions(), this)

        sendButton.setOnClickListener {

        }

        receiveButton.setOnClickListener {
            startActivity(Intent(this, ReceiveActivity::class.java))
        }
    }

    private fun getTransactions() : ArrayList<Any> {
        return if (this::mTransactions.isInitialized) {
            mTransactions
        } else {
            mTransactions = loadData()
            mTransactions
        }
    }

    private fun loadData() : ArrayList<Any> {
        // TODO: Make a call to Rust
        val list = arrayListOf<Any>()
        list.add("Transactions")
        for (i in 0..9) {
            val txn = TxnListViewModel(TransactionType.RECEIVED, "Sept 1, 2019", "21.00")
            list.add(txn)
        }
        return list
    }
}
