package com.udacity.asteroidradar.main.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.main.view_model.MainViewModel
import com.udacity.asteroidradar.pojo.Asteroid

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAsteroidAdapter
    val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
    }

    private fun setupRecyclerView() {
        adapter = MainAsteroidAdapter(MainAsteroidAdapter.AsteroidListener { asteroid ->
            viewModel.onAsteroidClicked(asteroid)
        })
        binding.asteroidRecycler.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.asteroids.observe(viewLifecycleOwner) { asteroids ->
            if (asteroids != null) {
                adapter.submitList(asteroids)
            }
        }

        viewModel.navigateToDetailFragment.observe(viewLifecycleOwner) { asteroid ->
            if (asteroid != null) {
                navigateToDetailFragment(asteroid)
                viewModel.doneNavigating()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.view_week_asteroids_menu -> viewModel.onViewWeekAsteroidsClicked()
            R.id.view_today_asteroids_menu -> viewModel.onTodayAsteroidsClicked()
            R.id.view_saved_asteroids_menu -> viewModel.onSavedAsteroidsClicked()
        }
        return true
    }
    private fun navigateToDetailFragment(asteroid: Asteroid) {
        findNavController().navigate(
            MainFragmentDirections.actionShowDetail(
                asteroid
            )
        )
    }
}