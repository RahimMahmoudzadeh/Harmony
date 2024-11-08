package com.rahim.harmony.feature.musicDetail

import com.rahim.harmony.core.base.UnidirectionalViewModel

interface MusicDetailContract :
    UnidirectionalViewModel<MusicDetailContract.MusicDetailEvent, MusicDetailContract.MusicDetailState> {
    sealed class MusicDetailEvent() {

    }

    data class MusicDetailState(
        val loading: Boolean = true,
        val data: String = "",
        val error: String = ""
    )
}