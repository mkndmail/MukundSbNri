package com.example.mukundsbnri

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mukundsbnri.database.RepoDatabase
import com.example.mukundsbnri.database.getDatabase
import com.example.mukundsbnri.databinding.ActivityMainBinding
import com.example.mukundsbnri.viewmodels.MainViewModel
import com.example.mukundsbnri.viewmodels.MainViewModelFactory

/**
 * Created by Mukund, mkndmail@gmail.com on 30, May, 2020
 */

class MainActivity : AppCompatActivity(), ItemClicked {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var repoAdapter: RepoAdapter
    private lateinit var database: RepoDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        ).get(MainViewModel::class.java)
        binding.myViewModel = mainViewModel
        database = getDatabase(this)
        initAdapter()
        setUpRefreshListener()
        mainViewModel.showProgressBar.observe(this, Observer {
            binding.swipeRefreshLayout.isRefreshing = it
            if (it)
                binding.progressBar.visibility = VISIBLE
            else
                binding.progressBar.visibility = GONE

        })
        mainViewModel.responseData.observe(this, Observer { gitHubRepos ->
            val repositoryList = mainViewModel.getEntityFromDataBaseModel(gitHubRepos)
            mainViewModel.insertAll(repositoryList)
        })
    }

    private fun setUpRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            try {
                mainViewModel.callApi2()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun initAdapter() {
        repoAdapter = RepoAdapter(this)
        binding.rvRepos.adapter = repoAdapter
        mainViewModel.allRepository.observe(this, Observer { listOfRepos ->
            repoAdapter.submitList(listOfRepos)
        })
    }

    override fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}