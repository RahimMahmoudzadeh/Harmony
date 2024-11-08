package com.rahim.harmony.feature.home

import com.rahim.harmony.core.base.UnidirectionalViewModel
import com.rahim.harmony.domain.offlineMusic.MusicCategory
import com.rahim.harmony.domain.offlineMusic.model.OfflineMusic

interface HomeContract: UnidirectionalViewModel<HomeContract.HomeEvent, HomeContract.HomeState> {
    sealed class HomeEvent() {

    }

    data class HomeState(
        val loading: Boolean = true,
        val categoryName: List<String> = emptyList(),
        val offlineMusic: List<OfflineMusic> = emptyList(),
        val onlineMusic: List<OfflineMusic> = emptyList(),
        val error: String = ""
    )
}