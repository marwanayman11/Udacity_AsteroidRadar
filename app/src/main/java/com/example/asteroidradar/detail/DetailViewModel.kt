package com.example.asteroidradar.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asteroidradar.Asteroids

class DetailViewModel(asteroid: Asteroids) : ViewModel() {
    private val _selectedAsteroid = MutableLiveData<Asteroids>()

    val selectedAsteroid: LiveData<Asteroids>
        get() = _selectedAsteroid

    init {
        _selectedAsteroid.value = asteroid
    }

    fun format1(double: Double): String {
        val string = double.toString()
        return "$string  au"
    }

    fun format2(double: Double): String {
        val string = double.toString()
        return "$string  km"
    }

    fun format3(double: Double): String {
        val string = double.toString()
        return "$string  km/s"
    }

}