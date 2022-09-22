package com.dicoding.jetreward.model

data class Reward(
    val id: Long,
    val image: Int,
    val title: String,
    val requiredPoint: Int,
)