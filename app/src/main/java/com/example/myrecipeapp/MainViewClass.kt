    package com.example.myrecipeapp

    import androidx.compose.runtime.State
    import androidx.compose.runtime.mutableStateOf
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import kotlinx.coroutines.launch
    import android.util.Log


    class MainViewClass: ViewModel() {

        private val _categorieState = mutableStateOf(RecipeState())
        val categoriesState: State<RecipeState> = _categorieState   // When when we update our RecipeState we need to stay updated.So we need state
        init {
            fetchCategories()
        }

        private fun fetchCategories() {
            viewModelScope.launch {
                try {
                    val response = recipeService.getCategories()
                    _categorieState.value = _categorieState.value.copy(
                        list = response.categories,
                        loading = false,
                        error = null
                    )
                } catch (e: Exception) {
                    _categorieState.value = _categorieState.value.copy(
                        loading = false,
                        error = "Error fetching Categories: ${e.message}"
                    )
                    e.printStackTrace() // Print the stack trace for more details
                    Log.e("MyRecipeApp", "Error fetching Categories: ${e.message}")
                }
            }
        }

        data class RecipeState(
            val loading : Boolean = true,
            val list: List<category> = emptyList(),
            val error: String? = null
        )
    }