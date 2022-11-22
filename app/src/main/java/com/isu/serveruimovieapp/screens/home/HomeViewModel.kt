package com.isu.serveruimovieapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isu.serveruimovieapp.domain.repository.GetUIScreenRepository
import com.isu.serveruimovieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getUIScreenRepository: GetUIScreenRepository): ViewModel(){

    var uiScreenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState())
        private set


    init {
        getUiScreen()
    }

    private fun getUiScreen() {
        viewModelScope.launch {
            getUIScreenRepository.getUiScreen(
            ).collect { result ->
                uiScreenState.emit(
                    uiScreenState.value.copy(uiScreenResponse = null,
                        loading = false,
                        error = null))
                when (result) {
                    is Resource.Error -> {
                        uiScreenState.emit(
                            uiScreenState.value.copy(uiScreenResponse = null,
                                loading = false,
                                error = result.message))
                    }
                    is Resource.Loading -> {
                        uiScreenState.emit(uiScreenState.value.copy(
                            uiScreenResponse = null,
                            loading = true,
                            error = null))
                    }

                    is Resource.Success -> {
                        uiScreenState.emit(uiScreenState.value.copy(
                            uiScreenResponse = result.data,
                            loading = false,
                            error = null))
                    }
                }
            }
        }
    }


}