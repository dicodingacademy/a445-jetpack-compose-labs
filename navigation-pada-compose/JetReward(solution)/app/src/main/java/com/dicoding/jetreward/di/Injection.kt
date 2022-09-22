package com.dicoding.jetreward.di

import com.dicoding.jetreward.data.RewardRepository


object Injection {
    fun provideRepository(): RewardRepository {
        return RewardRepository.getInstance()
    }
}