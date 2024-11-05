package com.rahim.harmony.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(), HomeContract {

    val mutableState = MutableStateFlow(HomeContract.HomeState())
    override val state: StateFlow<HomeContract.HomeState> = mutableState

    override fun event(event: HomeContract.HomeEvent) {

    }
}