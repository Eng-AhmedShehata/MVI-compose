package com.cat.mvicompose.home

// ViewState (VS): reflect ui state
data class HomeViewState(
    var isLoading: Boolean = false,
    var usersList: List<User>? = null,
)