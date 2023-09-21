package com.example.movie.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.domain.enitity.MovieEntity
import com.example.movie.presentation.MovieApplication
import com.example.movie.presentation.ViewModelFactory
import com.example.movie.presentation.adapters.MovieAdapter
import com.example.movie.presentation.viewModel.MovieViewModel
import javax.inject.Inject

class MovieFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieViewModel

    private val component by lazy {
        (requireActivity().application as MovieApplication).component
    }

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieBinding == null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]
        val adapter = MovieAdapter(object : MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: MovieEntity) {
                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movie)
                )
            }
        })
        binding.recyclerViewMovies.adapter = adapter
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(context, 2)
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.progress.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        binding.recyclerViewMovies.recycledViewPool.setMaxRecycledViews(R.layout.movie_card, 20)

        binding.recyclerViewMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val isLastItem =
                    lastVisibleItemPosition >= (recyclerView.adapter?.itemCount ?: 0) - 10
                if (isLastItem) {
                    viewModel.loadMoreMovies()
                }
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.itemFavourite -> {
                findNavController().navigate(
                    R.id.action_movieFragment_to_favouriteMovieFragment2
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}