package com.vrares.flowerorders.view

import com.vrares.flowerorders.model.models.Order

interface OnOrderClickListener {
    fun onOrderClicked(order: Order)
}