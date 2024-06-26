package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel: MainViewClass = viewModel()
    val viewState by recipeViewModel.categoriesState
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(_viewState = viewState, navigateToDetail = {
                // This part is responsible for passing data from current screen to detail screen.
                // Al this data is stored in currentBckStack
                // It utilizes the savedStateHandle. which is a component of Compose navigation framework
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route=Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<category>("cat")?: category("","", "","")
            CategoryDetailScreen(category = category)
        }
    }
}