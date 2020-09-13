package com.vrares.flowerorders.model

import com.google.gson.GsonBuilder
import com.vrares.flowerorders.model.networking.OrdersService
import com.vrares.flowerorders.viewmodel.NetworkingCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OrdersRepository {

    companion object {
        const val BASE_URL = "http://demo1875631.mockable.io"
    }

    private val requestInterface: OrdersService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(OrdersService::class.java)

    fun getOrders(networkingCallback: NetworkingCallback): Disposable {
        return requestInterface.getOrders()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result -> networkingCallback.onListReceived(result) },
                { throwable -> networkingCallback.unError(throwable) }
            )
    }

}