package com.vrares.flowerorders.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vrares.flowerorders.model.OrdersRepository
import com.vrares.flowerorders.model.models.Order
import io.reactivex.disposables.CompositeDisposable

class OrdersViewModel {

    private var myCompositeDisposable: CompositeDisposable? = null
    private val ordersRepository = OrdersRepository()

    private val ordersList = MutableLiveData<List<Order>>()
    private val error = MutableLiveData<Throwable>()

    var isListEmpty = false
    var isLoading = true

    init {
        myCompositeDisposable = CompositeDisposable()
        myCompositeDisposable?.add(ordersRepository.getOrders(networkingCallback = object :
            NetworkingCallback {
            override fun onListReceived(list: List<Order>) {
                isLoading = false
                isListEmpty = list.isEmpty()
                ordersList.value = list
            }

            override fun unError(throwable: Throwable) {
                isLoading = false
                error.value = throwable
            }

        }))
    }

    fun getOrders(): MutableLiveData<List<Order>> {
        return ordersList
    }

    fun getError(): MutableLiveData<Throwable> {
        return error
    }

}