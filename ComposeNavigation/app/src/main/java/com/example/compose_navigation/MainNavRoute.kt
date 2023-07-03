package com.example.compose_navigation

sealed class MainNavRoute(val route: String)

object Home: MainNavRoute("home")
object Settings: MainNavRoute("settings")
object Profile: MainNavRoute("profile")
