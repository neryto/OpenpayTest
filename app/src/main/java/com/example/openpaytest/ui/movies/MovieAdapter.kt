package com.example.openpaytest.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.openpaytest.BuildConfig
import com.example.openpaytest.R
import com.example.openpaytest.databinding.ItemMovieBinding
import com.example.openpaytest.extensions.loadImage
import com.example.openpaytest_data.models.Movie

class MovieAdapter constructor(
    private var onItemClicked : ((item: Movie) -> Unit)? = null
) : ListAdapter<Movie,MovieAdapter.MovieViewHolder>(MyDiffCallback()) {
    class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemView: Movie,onItemClicked : ((item: Movie) -> Unit)?) {
            binding.apply {
                root.setOnClickListener {
                    onItemClicked?.invoke(itemView)
                }
                rate.text = binding.root.context
                    .getString(R.string.rate, itemView.voteAverage.toString())
                year.text = itemView.releaseDate
                title.text = itemView.title
                poster.loadImage("${BuildConfig.IMAGE_URL}${itemView.posterPath}")

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val mView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val binding: ItemMovieBinding = ItemMovieBinding.bind(mView)
        return MovieViewHolder(binding)
    }



    // override fun getItemCount(): Int = ls.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position),onItemClicked)
    }

}

class MyDiffCallback : DiffUtil.ItemCallback<Movie>(){
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean  =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean  = oldItem == newItem



}