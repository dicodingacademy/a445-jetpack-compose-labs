package com.dicoding.jetreward.data

import com.dicoding.jetreward.model.FakeRewardDataSource
import com.dicoding.jetreward.model.OrderReward
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RewardRepository {

    private val orderRewards = mutableListOf<OrderReward>()

    init {
        if (orderRewards.isEmpty()) {
            FakeRewardDataSource.dummyRewards.map {
                orderRewards.add(OrderReward(it, 0))
            }
        }
    }

    fun getAllRewards(): Flow<List<OrderReward>> {
        return flowOf(orderRewards)
    }

    companion object {
        @Volatile
        private var instance: RewardRepository? = null

        fun getInstance(): RewardRepository =
            instance ?: synchronized(this) {
                RewardRepository().apply {
                    instance = this
                }
            }
    }
}