package com.vrares.flowerorders.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.vdr.flowerordersapp.BR
import com.vdr.flowerordersapp.R
import com.vdr.flowerordersapp.databinding.ActivityOrdersBinding
import com.vrares.flowerorders.model.models.Order
import com.vrares.flowerorders.viewmodel.OrdersViewModel

class OrdersActivity : AppCompatActivity(), OnOrderClickListener {

    companion object {
        const val EXTRA_ORDER = "extraOrder"
    }

    private var viewModel: OrdersViewModel = OrdersViewModel()
    private lateinit var binding: ActivityOrdersBinding
    private val ordersList: MutableList<Order> = mutableListOf()
    private val adapter = OrdersAdapter(ordersList, this)

    private val ordersListObserver = Observer<List<Order>> { orders: List<Order>? ->
        run {
            with(ordersList) {
                clear()
                orders?.toMutableList()?.let { addAll(it) }
                binding.notifyPropertyChanged(BR.viewModel)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private val networkingErrorObserver = Observer<Throwable> { throwable ->
        run {
            if (throwable != null) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.error))
                builder.setMessage(throwable.message)
                builder.setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
                builder.show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_orders)
        binding.viewModel = viewModel
        viewModel.getOrders().observe(this, ordersListObserver)
        viewModel.getError().observe(this, networkingErrorObserver)
        binding.ordersRecyclerview.adapter = adapter
    }

    override fun onOrderClicked(order: Order) {
        val intent = Intent(this, OrderDetailsActivity::class.java)
        intent.putExtra(EXTRA_ORDER, order)
        startActivity(intent)

    }

}
