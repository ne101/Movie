package com.example.movie.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movie.databinding.FragmentMovieDetailBinding
import com.example.movie.presentation.MovieApplication
import com.example.movie.presentation.ViewModelFactory
import com.example.movie.presentation.viewModel.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailFragment : Fragment() {

    private val args by navArgs<MovieDetailFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieDetailViewModel


    private val component by lazy {
        (requireActivity().application as MovieApplication).component
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieDetailBinding == null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]





        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            binding.tvMovieName.text = movie.movieName
            binding.tvYear.text = movie.year
            binding.tvRating.text = movie.rating
            binding.tvDescription.text = movie.description
            Glide.with(this).load(movie.poster).into(binding.ivPoster)
            viewModel.favouriteMovies.observe(viewLifecycleOwner) { favouriteMovies ->
                if (favouriteMovies.any { it.movieId == movie.movieId }) {
                    val drawable =
                        ContextCompat.getDrawable(
                            requireContext(),
                            android.R.drawable.presence_online
                        )
                    binding.ivAddMovieToDB.setImageDrawable(drawable)
                }
            }
        }
        viewModel.loadDetailInfo(args.movie.movieId)

        binding.ivAddMovieToDB.setOnClickListener {
            if (binding.ivAddMovieToDB.drawable.constantState == ContextCompat.getDrawable(
                    requireContext(),
                    android.R.drawable.presence_invisible
                )?.constantState
            ) {
                viewModel.addMovieToDataBase(args.movie)
                val drawable =
                    ContextCompat.getDrawable(requireContext(), android.R.drawable.presence_online)
                binding.ivAddMovieToDB.setImageDrawable(drawable)
            } else {
                viewModel.removeMovieFromDataBase(args.movie)
                val drawable = ContextCompat.getDrawable(
                    requireContext(),
                    android.R.drawable.presence_invisible
                )
                binding.ivAddMovieToDB.setImageDrawable(drawable)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}