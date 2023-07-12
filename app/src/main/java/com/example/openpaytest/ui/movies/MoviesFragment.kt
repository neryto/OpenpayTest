package com.example.openpaytest.ui.movies

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.openpaytest.BuildConfig
import com.example.openpaytest.R
import com.example.openpaytest.base.BaseFragment
import com.example.openpaytest.databinding.FragmentMoviesBinding
import com.example.openpaytest.databinding.ItemDetailBinding
import com.example.openpaytest.extensions.loadImage
import com.example.openpaytest_data.models.Movie
import com.example.openpaytest_data.models.MovieType
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap.WRAP
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentMoviesBinding
    override fun setLayout(): Int = R.layout.fragment_movies

    private val viewModel: MoviesViewModel by viewModels()


    override fun setupView(view: View) {
        binding = FragmentMoviesBinding.bind(view).apply {
            with(spinnerMovies) {
                adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    MovieType.values()
                )
                onItemSelectedListener = object : OnItemSelectedListener,
                    AdapterView.OnItemClickListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val selectedItem = p0?.getItemAtPosition(p2) as MovieType
                        performGetMovies(selectedItem.getType())

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

                }


            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.movies.collect {
                        val myAdapter =  MovieAdapter() { movie ->
                            showDetail(movie)
                        }
                        myAdapter.submitList(it)
                        binding.recyclerView.apply {
                            adapter = myAdapter

                            layoutManager = FlexboxLayoutManager(
                                requireContext(),
                                FlexDirection.COLUMN, WRAP
                            )
                        }
                    }
                }

                launch {
                    viewModel.error.collect {
                        it?.let { notNullError ->
                            showError(notNullError)
                        }

                    }
                }

            }

        }
    }

    override fun startFragmentActions() {
    }

    private fun performGetMovies(type: String) {
        lifecycleScope.launch {
            viewModel.getMovies(type)
        }
    }

    private fun showDetail(item: Movie) {
        BottomSheetDialog(requireContext()).apply {
            val view: View = layoutInflater
                .inflate(R.layout.item_detail, this@MoviesFragment.binding.root, false)
            val binding = ItemDetailBinding.bind(view).apply {
                overview.text = item.overview
                banner.loadImage("${BuildConfig.IMAGE_URL}${item.posterPath}")
            }
            setCancelable(true)
            setContentView(binding.root)
            show()
        }
    }

}