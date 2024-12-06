package com.rahim.harmony.domain.offlineMusic

import com.rahim.harmony.domain.offlineMusic.model.OfflineMusic
import kotlinx.coroutines.flow.Flow

interface OfflineMusicRepo {
    suspend fun getOfflineCategory(): List<MusicCategory>
    suspend fun refreshMusic(): List<OfflineMusic>
    fun getMusic(): Flow<List<OfflineMusic>>
}