package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import java.util.Locale.Category



@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
                 _viewState:MainViewClass.RecipeState,
                 navigateToDetail : (category)-> Unit
                 ){

    Box(modifier = Modifier.fillMaxSize()){
        when{
            _viewState.loading->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            _viewState.error!=null->{
                Text("Error!")
            }
            else->{
                // Display categories
                CategoriesScreen(categories = _viewState.list,navigateToDetail)

            }
        }
    }
}
@Composable
fun CategoriesScreen(categories: List<category>,
                     navigateToDetail : (category)-> Unit
                     ){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(categories){
            category->
            CategoryItem(category = category,navigateToDetail)
        }
    }
}
// This composable takes care how each item looks like
@Composable
fun CategoryItem(category: category,
                 navigateToDetail : (category)-> Unit
                 ){
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable { navigateToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = category.strCategory, // Use the specific property you want to display
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)

        )
    }
}

