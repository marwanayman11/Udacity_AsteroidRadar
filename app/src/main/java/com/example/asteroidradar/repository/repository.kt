package com.example.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.asteroidradar.Asteroids
import com.example.asteroidradar.PictureOfDay
import com.example.asteroidradar.database.AsteroidsRoom
import com.example.asteroidradar.database.PictureOfTheDayRoom
import com.example.asteroidradar.database.asDomainModel
import com.example.asteroidradar.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidsRepository(private val database: AsteroidsRoom) {
    val asteroids: LiveData<List<Asteroids>> =
        Transformations.map(database.asteroidsDao.getAsteroids()) {
            it.asDomainModel()
        }
    val asteroidsT: LiveData<List<Asteroids>> =
        Transformations.map(database.asteroidsDao.getAsteroidsToday()) {
            it.asDomainModel()
        }
    val asteroidsW: LiveData<List<Asteroids>> =
        Transformations.map(database.asteroidsDao.getAsteroidsWeek()) {
            it.asDomainModel()
        }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {

            val json = AsteroidAPi.retrofitService.getAsteroids(Constants.API_KEY)
            val jsonObject = JSONObject(json)
            val result = parseAsteroidsJsonResult(jsonObject)
            database.asteroidsDao.insertAll(*result.asDatabaseModel())
            database.asteroidsDao.delete()

        }
    }


}

class PictureRepository(private val database: PictureOfTheDayRoom) {
    val picture: LiveData<PictureOfDay> =
        Transformations.map(database.pictureOfTheDayDao.getPictureOfTheDay()) {
            it?.asDomainModel()
        }

    suspend fun refreshPicture() {
        withContext(Dispatchers.IO) {
            val result = PictureAPi.retrofitService.getPictureOfTheDay(Constants.API_KEY)
            database.pictureOfTheDayDao.insert(result.asDatabaseModel())

        }
    }
}