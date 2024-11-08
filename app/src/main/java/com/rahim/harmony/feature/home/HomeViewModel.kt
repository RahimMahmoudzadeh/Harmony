package com.rahim.harmony.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahim.harmony.domain.offlineMusic.MusicCategory
import com.rahim.harmony.domain.offlineMusic.OfflineMusicRepo
import com.rahim.harmony.domain.offlineMusic.model.OfflineMusic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.forEach

@HiltViewModel
class HomeViewModel @Inject constructor(private val offlineMusicRepo: OfflineMusicRepo) :
    ViewModel(), HomeContract {

    val mutableState = MutableStateFlow(HomeContract.HomeState())
    override val state: StateFlow<HomeContract.HomeState> = mutableState.onStart {
        getCategory()
        getAllMusic()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HomeContract.HomeState())

    override fun event(event: HomeContract.HomeEvent) {

    }

    private fun getCategory() {
        viewModelScope.launch {
            val category = offlineMusicRepo.getOfflineCategory()
            val categoryName = convertCategoryMusicToTitle(category)
            mutableState.update {
                it.copy(categoryName = categoryName)
            }
        }
    }

    private fun getAllMusic() {
        viewModelScope.launch {
            val music = offlineMusicRepo.getOfflineMusic()
            mutableState.update {
                it.copy(offlineMusic = music)
            }
        }
    }

    private fun convertCategoryMusicToTitle(category: List<MusicCategory>): List<String> {
        val categoryName = ArrayList<String>()
        category.forEach {
            categoryName.add(
                when (it) {
                    MusicCategory.ENERGIZE -> "Energize"
                    MusicCategory.SAD -> "Sad"
                    MusicCategory.FOCUS -> "Focus"
                    MusicCategory.SLEEP -> "Sleep"
                    MusicCategory.RELAX -> "Relax"
                    MusicCategory.WORKOUT -> "Workout"
                    MusicCategory.PARTY -> "Party"
                }
            )
        }
        return categoryName
    }
}