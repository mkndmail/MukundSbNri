package com.example.mukundsbnri.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mukundsbnri.dao.RepoDao
import com.example.mukundsbnri.entity.RepoEntity

/**
 * Created by Mukund, mkndmail@gmail.com on 30, May, 2020
 */

@Database(entities = [RepoEntity::class], version = 1, exportSchema = false)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}
private lateinit var repoDatabase: RepoDatabase
fun getDatabase(context: Context): RepoDatabase {
    synchronized(RepoDatabase::class.java) {

        if (!::repoDatabase.isInitialized){
            repoDatabase = Room.databaseBuilder(
                context.applicationContext,
                RepoDatabase::class.java,
                "repo_db"
            ).build()
        }

    }
    return repoDatabase
}