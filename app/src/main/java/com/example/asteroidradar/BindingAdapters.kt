package com.example.asteroidradar
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

@BindingAdapter("pictureOfTheDay")
fun bindImage(imageView: ImageView, url: String?) {
    url?.let {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .error(R.drawable.ic_help_circle)
            .into(imageView)
    }

   // image of the day mediaType is video today so it will be ic_help_circle until tomorrow
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Asteroids>?) {
    val adapter = recyclerView.adapter as AsteroidAdapter
    adapter.submitList(data)

}

@BindingAdapter("statusImage")
fun ImageView.setStatusImage(asteroid: Asteroids?) {
    asteroid?.let {
        setImageResource(
            when (asteroid.isPotentiallyHazardous) {
                true -> R.drawable.ic_status_potentially_hazardous
                false -> R.drawable.ic_status_normal

            }
        )

    }
}

@BindingAdapter("statusImageDetail")
fun ImageView.setStatusImageDetail(asteroid: Asteroids?) {
    asteroid?.let {
        setImageResource(
            when (asteroid.isPotentiallyHazardous) {
                true -> R.drawable.asteroid_hazardous
                false -> R.drawable.asteroid_safe

            }
        )

    }
}