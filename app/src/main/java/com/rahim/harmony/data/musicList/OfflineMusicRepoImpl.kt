package com.rahim.harmony.data.musicList

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.rahim.harmony.domain.offlineMusic.MusicCategory
import com.rahim.harmony.domain.offlineMusic.OfflineMusicRepo
import com.rahim.harmony.domain.offlineMusic.model.OfflineMusic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OfflineMusicRepoImpl @Inject constructor(
    private val context: Context,
    private val coroutineScope: CoroutineScope = CoroutineScope(
        Dispatchers.IO
    ),
) : OfflineMusicRepo {

    val audioFiles = ArrayList<OfflineMusic>()
    val remoteMusic = ArrayList<OfflineMusic>()

    override fun getMusic(): Flow<List<OfflineMusic>> =
        flow { emit(if (remoteMusic.isEmpty()) audioFiles else remoteMusic) }

    override suspend fun getOfflineCategory() =
        listOf(
            MusicCategory.RELAX,
            MusicCategory.WORKOUT,
            MusicCategory.ENERGIZE,
            MusicCategory.PARTY,
            MusicCategory.FOCUS,
            MusicCategory.SAD,
            MusicCategory.SLEEP
        )

    override suspend fun refreshMusic(): List<OfflineMusic> {
        return coroutineScope.async {
            val contentResolver = context.contentResolver
            val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION,
            )
            val selection = null
            val selectionArgs = null
            val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

            contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)
                ?.use { cursor ->
                    while (cursor.moveToNext()) {
                        val id =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                        val title =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                        val artist =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                        val path =
                            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                        val albumId =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID))
                        val duration =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                        val albumArtUri = ContentUris.withAppendedId(
                            Uri.parse("content://media/external/audio/albumart"),
                            albumId
                        )
                        audioFiles.add(
                            OfflineMusic(
                                id = id,
                                name = title,
                                singer = artist,
                                path = path,
                                image = albumArtUri,
                                duration = duration,
                                isSaved = false,
                                time = 0
                            )
                        )
                    }
                }
            audioFiles
        }.await()
    }
}