package com.kimstik.social_media.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimstik.social_media.R
import com.kimstik.social_media.data.model.best.NetworkBook
import com.kimstik.social_media.data.model.carousel.NetworkCarousel

class RecyclerCarouselAdapter: RecyclerView.Adapter<RecyclerCarouselViewHolder>() {

    private var list: List<NetworkCarousel> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerCarouselViewHolder =
        RecyclerCarouselViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_main_carousel, parent, false))

    override fun onBindViewHolder(holder: RecyclerCarouselViewHolder, position: Int) {
        Glide.with(holder.itemView)
                .load(list[position].image)
                .into(holder.recyclerImage)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: List<NetworkCarousel>){
        this.list = list
        notifyDataSetChanged()

    }
}

class RecyclerCarouselViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val recyclerImage: ImageView = itemView.findViewById(R.id.recyclerImage)
}