package com.kimstik.social_media.util.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kimstik.social_media.data.repository.NetworkRepository
import com.kimstik.social_media.ui.fragments.viewmodel.MainViewModel
import com.kimstik.social_media.util.dispatcher.DispatcherProvider

class MainViewModelFactory(private val repos: NetworkRepository,
                           private val dispatchers: DispatcherProvider): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repos, dispatchers) as T
    }
}