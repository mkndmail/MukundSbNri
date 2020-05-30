package com.example.mukundsbnri.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mukundsbnri.database.getDatabase
import com.example.mukundsbnri.entity.RepoEntity
import com.example.mukundsbnri.models.GithubRepo
import com.example.mukundsbnri.network.Api
import com.example.mukundsbnri.repository.DataBaseRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Mukund, mkndmail@gmail.com on 30, May, 2020
 */

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val pageNumber = 1
    private var totalItemsToShow = 0
    private val repository: DataBaseRepository
    private val _responseData = MutableLiveData<List<GithubRepo>>()
    val responseData: LiveData<List<GithubRepo>>
        get() = _responseData

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar
    val allRepository: LiveData<List<RepoEntity>>

    init {
        callApi2()
        val repoDao = getDatabase(application).repoDao()
        repository = DataBaseRepository(repoDao)
        allRepository = repository.allRepos
    }

    fun callApi() {
        try {
            totalItemsToShow += 10
            viewModelScope.launch {
                _showProgressBar.value = true
                val service = Api.retrofitService
                Log.d("service", service.toString())
                val response = service.getGithubRepos(pageNumber, totalItemsToShow)
                Log.d("response_data", response[0].toString())
                _showProgressBar.value = false
                _responseData.value = response
            }
        } catch (e: Exception) {
            _showProgressBar.value = false
            Log.d("response_exception", e.toString())
        }
    }

    fun callApi2() {
        totalItemsToShow += 10
        /*viewModelScope.launch {*/
        _showProgressBar.value = true
        val service = Api.retrofitService.getGithubRepos2(/*pageNumber*/1, totalItemsToShow)
        service.enqueue(object : Callback<List<GithubRepo>?> {
            override fun onFailure(call: Call<List<GithubRepo>?>, t: Throwable) {
                _showProgressBar.value = false
                Log.d("response_exception", t.toString())
            }

            override fun onResponse(
                call: Call<List<GithubRepo>?>,
                response: Response<List<GithubRepo>?>
            ) {
                _showProgressBar.value = false
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        if ((response.body() as List<GithubRepo>).size > 0) {
                            _responseData.value = response.body()
                            Log.d("response_data", _responseData.value?.get(0).toString())
                        }
                    }
                }
            }
        })
        /*}*/
    }

    fun getEntityFromDataBaseModel(githubRepos: List<GithubRepo>): List<RepoEntity> {
        return githubRepos.map { githubRepo ->
            RepoEntity(
                id = githubRepo.id,
                name = githubRepo.name,
                openIssuesCount = githubRepo.openIssuesCount,
                licenceName = githubRepo.license?.name,
                description = githubRepo.description,
                admin = githubRepo.permissions.admin,
                pull = githubRepo.permissions.pull,
                push = githubRepo.permissions.push,
                url = githubRepo.url
            )
        }
    }

    fun insertAll(repoEntities: List<RepoEntity>) {
        viewModelScope.launch {
            repository.insertAll(repoEntities)
        }
    }
}