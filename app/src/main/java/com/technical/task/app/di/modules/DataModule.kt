package com.technical.task.app.di.modules

import com.technical.task.data.races.RaceRepositoryImp
import com.technical.task.domain.races.repository.RaceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
  @Binds
  @Singleton
  abstract fun RaceRepositoryProvider(impl: RaceRepositoryImp): RaceRepository
}
