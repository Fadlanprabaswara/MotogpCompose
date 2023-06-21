package com.example.motogpcompose.model

import HeroesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class HeroRepository {

    private val orderHero = mutableListOf<OrderHero>()

    init {
        if (orderHero.isEmpty()){
            HeroesData.heroes.forEach{
                orderHero.add(OrderHero(it,0))
            }
        }
    }

    fun getAllHero(): Flow<List<OrderHero>> {
        return flowOf(orderHero)
    }
    fun getOrderRewardById(rewardId: Long): OrderHero {
        return orderHero.first {
            it.hero.id == rewardId
        }
    }

    companion object {
        @Volatile
        private var instance: HeroRepository? = null

        fun getInstance(): HeroRepository =
            instance ?: synchronized(this) {
                HeroRepository().apply {
                    instance = this
                }
            }
    }
}