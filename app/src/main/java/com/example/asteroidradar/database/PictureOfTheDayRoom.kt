package com.example.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PictureOfTheDayDao {
    @Query("SELECT * from pictureofthedaydatabase where id = 1")
    fun getPictureOfTheDay(): LiveData<PictureOfTheDayDatabase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg pictureOfTheDayDatabase: PictureOfTheDayDatabase)
}

@Database(entities = [PictureOfTheDayDatabase::class], version = 1)
abstract class PictureOfTheDayRoom : RoomDatabase() {
    abstract val pictureOfTheDayDao: PictureOfTheDayDao
}

private lateinit var INSTANCE: PictureOfTheDayRoom
fun getPictureDatabase(context: Context): PictureOfTheDayRoom {
    synchronized(PictureOfTheDayRoom::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PictureOfTheDayRoom::class.java,
                "picture"
            ).build()
        }
    }
    return INSTANCE
}