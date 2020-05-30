package com.example.mukundsbnri.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mukundsbnri.entity.RepoEntity

/**
 * Created by Mukund, mkndmail@gmail.com on 30, May, 2020
 */

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo_list")
     fun getAllRepos():LiveData<List<RepoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RepoEntity>)
}