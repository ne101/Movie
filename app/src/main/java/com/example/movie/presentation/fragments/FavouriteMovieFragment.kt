package com.example.movie.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.databinding.FragmentFavouriteMovieBinding
import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.presentation.MovieApplication
import com.example.movie.presentation.ViewModelFactory
import com.example.movie.presentation.adapters.MovieAdapter
import com.example.movie.presentation.viewModel.FavouriteMovieViewModel
import javax.inject.Inject


class FavouriteMovieFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: FavouriteMovieViewModel

    private val component by lazy {
        (requireActivity().application as MovieApplication).component
    }

    private var _binding: FragmentFavouriteMovieBinding? = null
    private val binding: FragmentFavouriteMovieBinding
        get() = _binding ?: throw RuntimeException("FragmentFavouriteMovieBinding == null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavouriteMovieViewModel::class.java]

        val adapter = MovieAdapter(object : MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: MovieEntity) {
                findNavController().navigate(
                    FavouriteMovieFragmentDirections.actionFavouriteMovieFragmentToMovieDetailFragment(
                        movie
                    )
                )
            }
        })
        binding.recyclerViewFavouriteMovies.adapter = adapter
        binding.recyclerViewFavouriteMovies.layoutManager = GridLayoutManager(context, 2)
        viewModel.movie.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}