package com.vrares.flowerorders.model.models

import java.io.Serializable

data class Order(
    val id: Int,
    val price: Int,
    val description: String,
    val deliver_to: String,
    val from: String
) : Serializable