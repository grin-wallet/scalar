package com.scalar.scalar.ui.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.scalar.scalar.ui.activities.BaseActivity
import com.scalar.scalar.R
import com.scalar.scalar.adapters.TransactionsAdapter
import com.scalar.scalar.models.TxnListViewModel
import com.scalar.scalar.models.TransactionType
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    lateinit var mTransactions : ArrayList<TxnListViewModel>

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

    }

    private fun getTransactions() : ArrayList<TxnListViewModel> {
        return if (this::mTransactions.isInitialized) {
            mTransactions
        } else {
            mTransactions = loadData()
            mTransactions
        }
    }

    private fun loadData() : ArrayList<TxnListViewModel> {
        // TODO: Make a call to Rust
        val txns = arrayListOf<TxnListViewModel>()
        for (i in 0..9) {
            val txn = TxnListViewModel(TransactionType.RECEIVED, "Sept 1, 2019", "21.00")
            txns.add(txn)
        }
        return txns
    }
}
