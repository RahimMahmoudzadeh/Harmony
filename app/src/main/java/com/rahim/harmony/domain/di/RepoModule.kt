package com.rahim.harmony.domain.di

import android.content.Context
import com.rahim.harmony.data.musicList.OfflineMusicRepoImpl
import com.rahim.harmony.domain.offlineMusic.OfflineMusicRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

}