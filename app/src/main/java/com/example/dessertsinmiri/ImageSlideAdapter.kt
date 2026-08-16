package com.example.dessertsinmiri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageSliderAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    // Task 3: ViewHolder for image slider items
    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    // Task 3: Inflate image slider item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_slider, parent, false)
        return ImageViewHolder(view)
    }

    // Task 3: Load and display images using Glide
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(holder.imageView.context)
            .load(images[position])
            .transition(DrawableTransitionOptions.withCrossFade())
            .override(800, 800) // Set image dimensions
            .fitCenter() // Maintain aspect ratio
            .into(holder.imageView)
    }

    // Task 3: Return number of images
    override fun getItemCount(): Int = images.size
}