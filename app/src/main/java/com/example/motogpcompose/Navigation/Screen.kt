package com.example.motogpcompose.Navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailHero : Screen("home/{heroId}") {
        fun createRoute(heroId: Long) = "home/$heroId"
    }
}
