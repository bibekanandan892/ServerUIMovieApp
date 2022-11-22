package com.isu.serveruimovieapp.data.remote

import com.isu.serveruimovieapp.data.model.UIScreenResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("get_screen")
    suspend fun getUIScreen(): Response<UIScreenResponse>

}