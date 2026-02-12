package com.pedrogv.camisetas.di

import com.pedrogv.camisetas.data.repository.CamisetaRepositoryImpl
import com.pedrogv.camisetas.domain.repository.CamisetaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCamisetaRepository(
        impl: CamisetaRepositoryImpl
    ): CamisetaRepository
}
