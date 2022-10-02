package com.example.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.asteroidradar.PictureOfDay

@Entity
data class PictureOfTheDayDatabase constructor(
    @PrimaryKey
    val id: Long = 1L,
    val mediaType: String,
    val title: String,
    val url: String
)

fun PictureOfTheDayDatabase.asDomainModel(): PictureOfDay {
    return PictureOfDay(

        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )

}