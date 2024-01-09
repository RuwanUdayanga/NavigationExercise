package com.example.navigationexercise

sealed class Screen(val route: String){
    object MainScreen : Screen(route= "main_screen")
    object DetailScreen : Screen(route= "detail_screen")
    object DetailScreen2 : Screen(route= "detail_screen2")


    fun withArgs(vararg args: String?): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
