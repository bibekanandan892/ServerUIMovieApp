package com.isu.serveruimovieapp.data.repository

import com.isu.serveruimovieapp.data.model.UIScreenResponse
import com.isu.serveruimovieapp.data.remote.ApiService
import com.isu.serveruimovieapp.domain.repository.GetUIScreenRepository
import com.isu.serveruimovieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUiScreenRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    GetUIScreenRepository {
    override suspend fun getUiScreen(): Flow<Resource<UIScreenResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val apiResponse =
                    apiService.getUIScreen()
                if(apiResponse.isSuccessful &&  apiResponse.body() != null ){
                    emit(Resource.Success(apiResponse.body()))
                }else{
                    emit(Resource.Error(apiResponse.errorBody().toString()))
                }
            } catch (e: IOException) {
                e.message?.let { emit(Resource.Error(it)) }
            } catch (e: HttpException) {
                e.message?.let { emit(Resource.Error(it)) }
            } catch (e: IllegalStateException) {
                e.message?.let { emit(Resource.Error(it)) }
            }
        }
    }
}