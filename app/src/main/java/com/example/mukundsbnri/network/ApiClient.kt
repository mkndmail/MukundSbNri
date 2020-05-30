package com.example.mukundsbnri.network

import com.example.mukundsbnri.models.GithubRepo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mukund, mkndmail@gmail.com on 30, May, 2020
 */

private const val BASE_URL = "https://api.github.com/orgs/octokit/"

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface DataService {
    @GET("repos")
    suspend fun getGithubRepos(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): List<GithubRepo>

    @GET("repos")
     fun getGithubRepos2(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): Call<List<GithubRepo>>
}

object Api {
    val retrofitService: DataService by lazy {
        retrofit.create(DataService::class.java)
    }
}