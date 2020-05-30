package com.example.mukundsbnri.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Mukund, mkndmail@gmail.com on 30, May, 2020
 */

class MainViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mApplication ) as T
    }

}