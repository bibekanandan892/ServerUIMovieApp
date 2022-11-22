package com.isu.serveruimovieapp.domain.repository

import com.isu.serveruimovieapp.data.model.UIScreenResponse
import com.isu.serveruimovieapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetUIScreenRepository {

    suspend fun getUiScreen() : Flow<Resource<UIScreenResponse>>

}