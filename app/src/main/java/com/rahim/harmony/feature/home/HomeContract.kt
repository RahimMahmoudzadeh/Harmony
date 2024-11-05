package com.rahim.harmony.feature.home

import com.rahim.harmony.core.base.UnidirectionalViewModel

interface HomeContract: UnidirectionalViewModel<HomeContract.HomeEvent, HomeContract.HomeState> {
    sealed class HomeEvent() {

    }

    data class HomeState(
        val loading: Boolean = true,
        val data: String = "",
        val error: String = ""
    )
}