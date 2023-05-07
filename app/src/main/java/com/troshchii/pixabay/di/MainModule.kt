package com.troshchii.pixabay.di

import com.troshchii.pixabay.data.HitsRepository
import com.troshchii.pixabay.data.HitsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainModule {

    @Binds
    abstract fun hitsRepository(repository: HitsRepositoryImpl): HitsRepository

}
