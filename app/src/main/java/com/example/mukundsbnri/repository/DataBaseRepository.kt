package com.example.mukundsbnri.repository

import androidx.lifecycle.LiveData
import com.example.mukundsbnri.dao.RepoDao
import com.example.mukundsbnri.entity.RepoEntity
import com.example.mukundsbnri.models.GithubRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Entity

/**
 * Created by Mukund, mkndmail@gmail.com on 30, May, 2020
 */

class DataBaseRepository (private val repoDao: RepoDao){

    val allRepos:LiveData<List<RepoEntity>> =repoDao.getAllRepos()

    suspend fun insertAll(repos:List<RepoEntity>){
        repoDao.insertAll(repos)
    }

}