package com.gmail.harsh_chuck.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.harsh_chuck.data.chuckApi.response.JokeRandomResponse

@Dao
interface JokeDao {
    @Query("SELECT * from joke_table")
    fun getAllJokes(): LiveData<List<JokeRandomResponse?>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(joke: JokeRandomResponse?)

    @Query("DELETE FROM joke_table")
    fun deleteAll()
}