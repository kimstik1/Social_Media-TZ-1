package com.kimstik.social_media.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimstik.social_media.R
import com.kimstik.social_media.data.model.best.NetworkBook
import com.kimstik.social_media.ui.adapters.listeners.BestRecyclerItemListener

class RecyclerBestAdapter(private val listener: BestRecyclerItemListener): RecyclerView.Adapter<RecyclerBestViewHolder>() {

    private var list: List<NetworkBook> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerBestViewHolder = RecyclerBestViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_best_seller, parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerBestViewHolder, position: Int) {
        holder.cartTitle.text = list[position].title
        holder.cartAuthor.text = list[position].author
        holder.cartPrice.text = list[position].price.toString()
        holder.cartRating.text = list[position].rate.score.toString()
        holder.cartAmount.text = "(${list[position].rate.amount.toString()})"

        Glide.with(holder.itemView)
                .load(list[position].image)
                .fitCenter()
                .into(holder.cartImage)

        holder.itemView.setOnClickListener {listener.onClick(position)}
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: List<NetworkBook>){
        this.list = list
        notifyDataSetChanged()
    }
}

class RecyclerBestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val cartImage: ImageView = itemView.findViewById(R.id.cartImage)
    val cartTitle: TextView = itemView.findViewById(R.id.cartTitle)
    val cartAuthor: TextView = itemView.findViewById(R.id.cartAuthor)
    val cartPrice: TextView = itemView.findViewById(R.id.cartPrice)
    val cartRating: TextView = itemView.findViewById(R.id.cartRating)
    val cartAmount: TextView = itemView.findViewById(R.id.cartAmount)
}