package com.example.responsiveadaptive

sealed class Routes(val route: String) {
    object Register : Routes("register")
    object RegisterSuccess : Routes("register_success")
    object Login : Routes("login")
}