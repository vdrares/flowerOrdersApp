package com.vrares.flowerorders.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.vdr.flowerordersapp.R
import com.vdr.flowerordersapp.databinding.ActivityOrderDetailsBinding
import com.vrares.flowerorders.model.models.Order
import com.vrares.flowerorders.view.OrdersActivity.Companion.EXTRA_ORDER

class OrderDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_details)
        binding.order = intent.getSerializableExtra(EXTRA_ORDER) as Order?
    }
}