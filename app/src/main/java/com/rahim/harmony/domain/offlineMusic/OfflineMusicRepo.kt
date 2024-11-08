package com.rahim.harmony.domain.offlineMusic

import com.rahim.harmony.domain.offlineMusic.model.OfflineMusic

interface OfflineMusicRepo {
    suspend fun getOfflineCategory(): List<MusicCategory>
    suspend fun getOfflineMusic(): List<OfflineMusic>
}