package com.example.myrecipeapp

sealed class Screen (val route: String){
    object RecipeScreen:Screen("RecipeScreen")    // Its a global static variable that cannot be changed
    object DetailScreen:Screen("DetailScreen")
}