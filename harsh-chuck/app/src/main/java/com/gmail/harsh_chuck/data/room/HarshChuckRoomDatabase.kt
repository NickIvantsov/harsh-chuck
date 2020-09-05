package com.gmail.harsh_chuck.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.harsh_chuck.data.chuckApi.response.CategoriesConverter
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse

@Database(entities = [JokeRandomResponse::class], version = 1, exportSchema = false)
@TypeConverters(CategoriesConverter::class)
abstract class HarshChuckRoomDatabase : RoomDatabase() {

    abstract fun jokeDao(): JokeDao
}
