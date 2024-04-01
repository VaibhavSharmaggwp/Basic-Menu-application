package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class category(val idCategory : String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String):Parcelable // It makes object in form of String
// Deserialization means changing string back to objects

data class CategoriesResponse(val categories: List<category>)