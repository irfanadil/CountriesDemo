package com.demo.countriesdemo.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.countriesdemo.MyApp
import com.demo.countriesdemo.R
import com.demo.countriesdemo.databinding.ActivityMainBinding
import com.demo.countriesdemo.model.ApiResponse
import com.demo.countriesdemo.ui.adapter.CountriesAdapter
import com.demo.countriesdemo.utils.viewModelFactory
import com.demo.jetpackdemoapp.ui.screens.MainViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    //private val mainViewModel: MainViewModel by viewModels()

    val mainViewModel: MainViewModel by viewModels {
        viewModelFactory {
            MainViewModel(MyApp.appLevelModule.repo)
        }
    }


    private val countriesAdpter = CountriesAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        countriesAdpter.setHasStableIds(true)
        binding.countriesRecycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.countriesAdpter
            setHasFixedSize(true)
            itemAnimator = null // Disable animations for better performance
            setItemViewCacheSize(20) // Cache more off-screen views
            recycledViewPool.setMaxRecycledViews(0, 20) // ViewType 0
        }
    }

    private fun observeData() {
        lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                mainViewModel.countriesFlow.collect{ response->
                    when(response){
                        is ApiResponse.Error -> { //
                            // Show some toast...
                        }
                        ApiResponse.Loading -> {
                            // Show Progress bar...
                        }
                        is ApiResponse.Success -> {
                            countriesAdpter.submitList(response.data )
                        }
                    }

                }
            }
        }
    }

}