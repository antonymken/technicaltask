package com.technical.task.app.di.modules

import com.technical.task.core.dispatchers.AppCoroutineDispatchers
import com.technical.task.core.dispatchers.DefaultCoroutineDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DispatchersModule {
  @Binds
  @Singleton
  abstract fun coroutineDispatchers(impl: DefaultCoroutineDispatchers): AppCoroutineDispatchers
}
