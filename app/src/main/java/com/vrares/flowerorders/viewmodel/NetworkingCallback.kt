package com.vrares.flowerorders.viewmodel

import com.vrares.flowerorders.model.models.Order

interface NetworkingCallback {

    fun onListReceived(list: List<Order>)
    fun unError(throwable: Throwable)

}