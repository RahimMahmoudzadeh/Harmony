package com.rahim.harmony.feature.main

import com.rahim.harmony.core.base.UnidirectionalViewModel
import com.rahim.harmony.domain.offlineMusic.MusicCategory
import com.rahim.harmony.domain.offlineMusic.model.OfflineMusic

interface MainContract: UnidirectionalViewModel<MainContract.MainEvent, MainContract.MainState> {
    sealed class MainEvent() {
        data object RefreshMusic
    }

    data class MainState(
        val loading: Boolean = true,
    )
}