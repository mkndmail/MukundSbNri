package com.example.mukundsbnri.models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Mukund, mkndmail@gmail.com on 30, May, 2020
 */

data class GithubRepo(
    @field:SerializedName("id") @PrimaryKey val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("open_issues_count") val openIssuesCount: Int,
    @field:SerializedName("license")  val license: License?,
    @field:SerializedName("description") val description:String,
    @field:SerializedName("html_url") val url: String,
    @field:SerializedName("permissions") val permissions: Permissions
)



data class License(
    @field:SerializedName("key") val key: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("spdx_id") val spdxId: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("node_id") val nodeId: String
)

data class Permissions(
    @field:SerializedName("admin") val admin: Boolean,
    @field:SerializedName("push") val push: Boolean,
    @field:SerializedName("pull") val pull: Boolean
)