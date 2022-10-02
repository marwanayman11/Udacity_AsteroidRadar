package com.example.asteroidradar.network

import com.example.asteroidradar.database.AsteroidsDatabase
import com.example.asteroidradar.database.PictureOfTheDayDatabase
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Asteroid(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

@JsonClass(generateAdapter = true)
data class NetworkAsteroidContainer(val asteroids: List<Asteroid>)

fun NetworkAsteroidContainer.asDatabaseModel(): Array<AsteroidsDatabase> {
    return asteroids.map {
        AsteroidsDatabase(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}

@JsonClass(generateAdapter = true)
data class PictureOfTheDayNetwork(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String
)

fun PictureOfTheDayNetwork.asDatabaseModel(): PictureOfTheDayDatabase {
    return PictureOfTheDayDatabase(
        id = 1L,
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )

}