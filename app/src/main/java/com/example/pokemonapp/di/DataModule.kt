package com.example.pokemonapp.di

import android.content.Context
import com.example.pokemonapp.data.PokemonsRepositoryImpl
import com.example.pokemonapp.data.api.ApiPokemonsDataSource
import com.example.pokemonapp.data.db.Dao
import com.example.pokemonapp.data.db.DbPokemonsDataSource
import com.example.pokemonapp.data.db.PokemonDb
import com.example.pokemonapp.data.utils.ImageUtils
import com.example.pokemonapp.data.utils.NetworkUtils
import com.example.pokemonapp.domain.PokemonsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providePokemonsRepository(
        apiSource: ApiPokemonsDataSource,
        dbSource: DbPokemonsDataSource,
        networkUtils: NetworkUtils
    ): PokemonsRepository{
        return PokemonsRepositoryImpl(
            apiDataSource = apiSource,
            dbDataSource = dbSource,
            networkUtils = networkUtils
        )
    }

    @Provides
    @Singleton
    fun provideApiDataSource() = ApiPokemonsDataSource()

    @Provides
    @Singleton
    fun provideDbDataSource(dao: Dao, imageUtils: ImageUtils) = DbPokemonsDataSource(
        dao = dao,
        imageUtils = imageUtils
    )

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context) = PokemonDb.getInstance(context).getDao()

    @Provides
    @Singleton
    fun provideImageUtils(@ApplicationContext context: Context) = ImageUtils(context)

    @Provides
    @Singleton
    fun provideNetworkUtils(@ApplicationContext context: Context) = NetworkUtils(context)
}