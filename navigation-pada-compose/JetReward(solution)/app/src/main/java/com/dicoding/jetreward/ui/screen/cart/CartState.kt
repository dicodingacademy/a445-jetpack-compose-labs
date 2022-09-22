package com.dicoding.jetreward.ui.screen.cart

import com.dicoding.jetreward.model.OrderReward

data class CartState(
    val orderReward: List<OrderReward>,
    val totalRequiredPoint: Int
)