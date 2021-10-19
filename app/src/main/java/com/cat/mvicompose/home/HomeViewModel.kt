package com.cat.mvicompose.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    private val homeRepository = HomeRepository()

    /** 1
     * Intent channel
     */
    val intentChannel = Channel<HomeIntent>(Channel.CONFLATED)

    /** 2
     * State flow
     */
    private val _stateChannel = MutableStateFlow(HomeViewState(isLoading = true))
    val stateChannel: StateFlow<HomeViewState> = _stateChannel

    init {
        //2 process
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                //process intents
                when(it) {
                    HomeIntent.GetUsers -> getUsersFromRepo()
                }
            }
        }
    }

    private fun getUsersFromRepo() {

        viewModelScope.launch {
            // 3
            val result = homeRepository.getUsers()
            //4 push
            val currentState = stateChannel.value.copy(
                isLoading = false,
                usersList = result
            )
            _stateChannel.emit(currentState)
        }
    }


}