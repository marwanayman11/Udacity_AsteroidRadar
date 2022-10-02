package com.example.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.asteroidradar.database.getDatabase
import com.example.asteroidradar.database.getPictureDatabase
import com.example.asteroidradar.repository.AsteroidsRepository
import com.example.asteroidradar.repository.PictureRepository
import retrofit2.HttpException

class RefreshDataWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_HOME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val asteroidsDatabase = getDatabase(applicationContext)
        val asteroidsRepository = AsteroidsRepository(asteroidsDatabase)
        val pictureOfTheDayDatabase = getPictureDatabase(applicationContext)
        val pictureRepository = PictureRepository(pictureOfTheDayDatabase)
        return try {
            asteroidsRepository.refreshAsteroids()
            pictureRepository.refreshPicture()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()

        }
    }

}