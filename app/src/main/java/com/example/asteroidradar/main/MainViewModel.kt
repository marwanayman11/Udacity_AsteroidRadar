package com.example.asteroidradar.main
import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.asteroidradar.Asteroids
import com.example.asteroidradar.database.getDatabase
import com.example.asteroidradar.database.getPictureDatabase
import com.example.asteroidradar.repository.AsteroidsRepository
import com.example.asteroidradar.repository.PictureRepository
import kotlinx.coroutines.launch
import timber.log.Timber
enum class Filter {
    TODAY,
    WEEK,
    All
}
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = AsteroidsRepository(database)
    private val pictureDatabase = getPictureDatabase(application)
    private val pictureRepository = PictureRepository(pictureDatabase)
    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroids>()
    val navigateToSelectedAsteroid: LiveData<Asteroids>
        get() = _navigateToSelectedAsteroid
    private var filter =MutableLiveData(Filter.All)
    val asteroids = Transformations.switchMap(filter){
        when(it!!){
            Filter.WEEK-> repository.asteroidsW
            Filter.TODAY->repository.asteroidsT
            else->repository.asteroids
        }
    }
    init {
        getPicture()
       getAsteroids()
    }

    val picture = pictureRepository.picture
    private fun getPicture() {
        viewModelScope.launch {
            try {
                pictureRepository.refreshPicture()
            } catch (e: Exception) {
                Timber.i("No connection")
            }
        }
    }

    private fun getAsteroids() {
        viewModelScope.launch {
            try {
                repository.refreshAsteroids()
            } catch (e: Exception) {
                Timber.i("No connection")
            }
        }
    }
    fun updateFilter(f: Filter){
        filter.postValue(f)
    }
    fun displayAsteroidDetails(asteroid: Asteroids) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }


}
