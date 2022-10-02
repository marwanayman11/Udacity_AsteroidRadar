package com.example.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface AsteroidsDao {

    @Query("SELECT * FROM asteroidsdatabase ORDER BY closeApproachDate")
    fun getAsteroids(): LiveData<List<AsteroidsDatabase>>

    @Query("SELECT * FROM asteroidsdatabase WHERE closeApproachDate = Date('now')")
    fun getAsteroidsToday(): LiveData<List<AsteroidsDatabase>>

    @Query("SELECT * FROM asteroidsdatabase WHERE closeApproachDate != Date('now') ORDER BY closeApproachDate")
    fun getAsteroidsWeek(): LiveData<List<AsteroidsDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: AsteroidsDatabase)

    @Query("DELETE FROM asteroidsdatabase WHERE closeApproachDate < Date('now')")
    suspend fun delete()
}

@Database(entities = [AsteroidsDatabase::class], version = 1)
abstract class AsteroidsRoom : RoomDatabase() {
    abstract val asteroidsDao: AsteroidsDao
}

private lateinit var INSTANCE: AsteroidsRoom
fun getDatabase(context: Context): AsteroidsRoom {
    synchronized(AsteroidsRoom::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AsteroidsRoom::class.java,
                "asteroids"
            ).fallbackToDestructiveMigration().build()
        }
    }
    return INSTANCE
}