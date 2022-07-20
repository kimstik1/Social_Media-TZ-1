package com.kimstik.social_media.util.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kimstik.social_media.data.repository.NetworkRepository
import com.kimstik.social_media.ui.fragments.viewmodel.ScopeViewModel
import com.kimstik.social_media.util.dispatcher.DispatcherProvider

class ScopeViewModelFactory(private val repos: NetworkRepository,
                            private val dispatchers: DispatcherProvider): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return ScopeViewModel(repos, dispatchers) as T
    }
}