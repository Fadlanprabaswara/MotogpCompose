package com.example.motogpcompose.model

object Injection {
    fun provideRepository(): HeroRepository {
        return HeroRepository.getInstance()
    }
}