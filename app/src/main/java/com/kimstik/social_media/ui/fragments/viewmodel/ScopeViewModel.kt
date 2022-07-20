package com.kimstik.social_media.ui.fragments.viewmodel

import android.util.Log
import com.kimstik.social_media.data.model.best.NetworkBook
import com.kimstik.social_media.data.model.similar.NetworkSimilar
import com.kimstik.social_media.data.repository.NetworkRepository
import com.kimstik.social_media.util.base.BaseViewModel
import com.kimstik.social_media.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ScopeViewModel(private val repos: NetworkRepository, dispatchers: DispatcherProvider) : BaseViewModel(dispatchers) {

    private val _similarList: MutableStateFlow<List<NetworkSimilar>> = MutableStateFlow(listOf())
    val similarList: StateFlow<List<NetworkSimilar>> get() = _similarList

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    init {
        scope.launch {
            try {
                val best = repos.getSimilar()
                best?.let {_similarList.emit(it)}
            }catch(ex: Exception){
                eventChannel.send(Event.ShowSnackBar(ex.toString()))
            }
        }
    }

    sealed class Event{
        data class ShowSnackBar(val text: String): Event()
    }
}