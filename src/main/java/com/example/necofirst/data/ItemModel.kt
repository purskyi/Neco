package com.example.necofirst.data

import com.example.necofirst.R

data class ItemModel(
    val imageId: Int,
    val title: String,
    val content: String
)
val items = listOf(
    ItemModel(imageId = R.drawable.android_superhero1, title = "Andrii", content = "Android DeveloperAndroid DeveloperAndroid DeveloperAndroid DeveloperAnd\nroid DeveloperA\nndroid DeveloperAnd\nroid DeveloperAndroid DeveloperAndroid \nDeveloperAndroid Developer"),

    ItemModel(imageId = R.drawable.android_superhero2, title = "Vasil", content = "C++ Developer"),
    ItemModel(imageId = R.drawable.android_superhero3, title = "Syava", content = "KSONZ"),
    ItemModel(imageId = R.drawable.android_superhero4, title = "Andrii", content = "Android Developer"),
    ItemModel(imageId = R.drawable.android_superhero5, title = "Andrii", content = "Android Developer"),
    ItemModel(imageId = R.drawable.android_superhero6, title = "Andrii", content = "Android Developer")

)
