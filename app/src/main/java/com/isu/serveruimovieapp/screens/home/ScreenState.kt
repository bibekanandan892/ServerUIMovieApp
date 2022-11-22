package com.isu.serveruimovieapp.screens.home

import com.isu.serveruimovieapp.data.model.UIScreenResponse

data class ScreenState(
    val uiScreenResponse: UIScreenResponse? = null,
    val loading : Boolean? = null,
    val error : String? = null
)
