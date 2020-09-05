package com.gmail.harsh_chuck.di

import android.app.Application
import androidx.room.Room
import com.gmail.harsh_chuck.data.room.HarshChuckRoomDatabase
import com.gmail.harsh_chuck.data.room.JokeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideHarshChuckRoomDatabase(application: Application): HarshChuckRoomDatabase {
        return Room.databaseBuilder(
            application,
            HarshChuckRoomDatabase::class.java,
            "harsh_chuck_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    @Provides
    fun provideJokeDao(pokemonDB: HarshChuckRoomDatabase): JokeDao {
        return pokemonDB.jokeDao()
    }
}