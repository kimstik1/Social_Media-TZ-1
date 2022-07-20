package com.kimstik.social_media.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimstik.social_media.R
import com.kimstik.social_media.data.model.carousel.NetworkCarousel
import com.kimstik.social_media.data.model.similar.NetworkSimilar

class RecyclerSimilarAdapter(): RecyclerView.Adapter<RecyclerSimilarViewHolder>() {

    private var list: List<NetworkSimilar> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerSimilarViewHolder =
        RecyclerSimilarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_similar, parent, false))

    override fun onBindViewHolder(holder: RecyclerSimilarViewHolder, position: Int) {
        Glide.with(holder.itemView)
                .load(list[position].image)
                .fitCenter()
                .into(holder.recyclerCartImage)
    }

    override fun getItemCount(): Int = list.size


    fun updateList(list: List<NetworkSimilar>){
        Log.wtf("UPDATE", list.toString())
        this.list = list
        notifyDataSetChanged()
    }
}

class RecyclerSimilarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    val recyclerCartImage: ImageView = itemView.findViewById(R.id.recyclerCartImage)
}