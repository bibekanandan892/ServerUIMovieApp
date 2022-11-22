package com.isu.serveruimovieapp.data.model

import com.isu.movieapp.model.MainScreen

data class UI(
    val id : String = "123",
    val mainScreen: MainScreen = MainScreen(),
    val rowItem: RowItem = RowItem(),
    val image: Image = Image(),
    val detailsColumn: DetailsColumn = DetailsColumn()
)
