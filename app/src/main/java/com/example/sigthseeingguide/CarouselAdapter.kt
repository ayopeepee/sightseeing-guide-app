package com.example.sigthseeingguide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarouselAdapter(private val images: List<CarouselModel>, private val context: Context) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.carousel_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {

        val image = images[position]
        Glide.with(context)
            .load(image.image)
            .into(holder.image)

    }

    class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.carousel_image_view)
    }
}