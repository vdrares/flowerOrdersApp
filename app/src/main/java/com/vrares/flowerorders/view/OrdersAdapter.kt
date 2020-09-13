package com.vrares.flowerorders.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vdr.flowerordersapp.R
import com.vdr.flowerordersapp.databinding.ItemOrderBinding
import com.vrares.flowerorders.model.models.Order

class OrdersAdapter(
    private val orders: List<Order>,
    private val orderClickListener: OnOrderClickListener
) :
    RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_order,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    inner class ViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.order = order
            binding.clickListener = orderClickListener
            binding.executePendingBindings()
        }
    }
}
