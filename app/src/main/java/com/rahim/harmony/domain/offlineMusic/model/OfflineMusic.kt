package com.rahim.harmony.domain.offlineMusic.model

import android.net.Uri

data class OfflineMusic(
    val id:Long,
    val name: String,
    val time: Long,
    val duration: Long,
    val image: Uri,
    val singer: String,
    val isSaved: Boolean,
    val path: String,
)
