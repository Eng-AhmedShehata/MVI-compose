package com.cat.mvicompose.home

// Intent: is what the ui want from VM
sealed class HomeIntent {
    object GetUsers : HomeIntent()
}
