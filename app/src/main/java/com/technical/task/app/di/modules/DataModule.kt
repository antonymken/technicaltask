package com.technical.task.app.di.modules

import com.technical.task.data.directory.EmployeeRepositoryImp
import com.technical.task.data.directory.di.EmployeeApiUrl
import com.technical.task.domain.directory.repository.EmployeeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    @Singleton
    abstract fun EmployeeRepositoryProvider(impl: EmployeeRepositoryImp): EmployeeRepository

    companion object {
        @EmployeeApiUrl
        @Provides
        fun provideRaceApiUrl(): String {
            return "https://s3.amazonaws.com/"
        }
    }
}
