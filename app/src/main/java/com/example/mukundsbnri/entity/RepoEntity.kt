package com.example.mukundsbnri.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Mukund, mkndmail@gmail.com on 30, May, 2020
 */

@Entity(tableName = "repo_list")
data class RepoEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "open_issues") val openIssuesCount: Int,
    @ColumnInfo(name = "licence_name") val licenceName: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name="admin") val admin:Boolean,
    @ColumnInfo(name="push") val push:Boolean,
    @ColumnInfo(name="pull") val pull:Boolean
)