package com.rahim.harmony.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahim.harmony.domain.offlineMusic.OfflineMusicRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val offlineMusicRepo: OfflineMusicRepo) :
    ViewModel(), MainContract {

    private val mutableState = MutableStateFlow(MainContract.MainState())
    override val state: StateFlow<MainContract.MainState> = mutableState

    override fun event(event: MainContract.MainEvent) = when (event) {
        MainContract.MainEvent.RefreshMusic -> {
            refreshMusic()
        }
    }
    private fun refreshMusic() {
        viewModelScope.launch{
            offlineMusicRepo.refreshMusic()
        }
    }
}