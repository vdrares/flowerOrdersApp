package com.vrares.flowerorders.model.networking

import com.vrares.flowerorders.model.models.Order
import io.reactivex.Observable
import retrofit2.http.GET

interface OrdersService {
    @GET("orders")
    fun getOrders(): Observable<List<Order>>
}