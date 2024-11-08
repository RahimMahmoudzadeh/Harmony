package com.rahim.harmony.feature.musicDetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MusicDetailViewModel @Inject constructor() : ViewModel(), MusicDetailContract {

    val mutableState = MutableStateFlow(MusicDetailContract.MusicDetailState())
    override val state: StateFlow<MusicDetailContract.MusicDetailState> = mutableState

    override fun event(event: MusicDetailContract.MusicDetailEvent) {

    }
}