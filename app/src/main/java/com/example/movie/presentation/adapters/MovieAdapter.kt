package com.example.movie.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.movie.databinding.MovieCardBinding
import com.example.movie.domain.enitity.MovieEntity

class MovieAdapter(private val clickListener: OnMovieClickListener) :
    ListAdapter<MovieEntity, MovieViewHolder>(MovieDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val binding = holder.binding
        val movieItem = getItem(position)
        holder.binding.root.setOnClickListener {
            clickListener.onMovieClick(movieItem)
        }
        with(binding) {
            with(movieItem) {
                tvRating.text = rating
                Glide.with(binding.root)
                    .load(poster)
                    .into(ivMovie)
            }
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: MovieEntity)
    }

}
