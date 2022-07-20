package com.kimstik.social_media.ui.fragments.viewmodel

import android.util.Log
import com.google.gson.Gson
import com.kimstik.social_media.data.model.best.NetworkBook
import com.kimstik.social_media.data.model.carousel.NetworkCarousel
import com.kimstik.social_media.data.repository.NetworkRepository
import com.kimstik.social_media.util.base.BaseViewModel
import com.kimstik.social_media.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repos: NetworkRepository, dispatchers: DispatcherProvider) : BaseViewModel(dispatchers) {

    private val _bestList: MutableStateFlow<List<NetworkBook>> = MutableStateFlow(listOf())
    val bestList: StateFlow<List<NetworkBook>> get() = _bestList

    private val _carouselList: MutableStateFlow<List<NetworkCarousel>> = MutableStateFlow(listOf())
    val carouselList: StateFlow<List<NetworkCarousel>> get() = _carouselList

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    init {
        scope.launch {
            try {
                val best = repos.getBestBooks()
                val carousel = repos.getCarousel()
                best?.let {_bestList.emit(it)}
                carousel?.let {_carouselList.emit(it)}
            }catch(ex: Exception){
                eventChannel.send(Event.ShowSnackBar(ex.toString()))
            }
        }
    }

    fun recyclerClickHandler(position: Int){
        scope.launch {
            val data = Gson().toJson(_bestList.value[position])
            eventChannel.send(Event.NavigateScopeFragment(data))
        }
    }

    sealed class Event{
        data class ShowSnackBar(val text: String): Event()
        data class NavigateScopeFragment(val text: String): Event()
    }
}