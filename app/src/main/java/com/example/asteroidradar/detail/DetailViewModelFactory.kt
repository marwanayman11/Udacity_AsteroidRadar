package com.example.asteroidradar.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.asteroidradar.Asteroids

class DetailViewModelFactory(
    private val asteroid: Asteroids,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(asteroid) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}