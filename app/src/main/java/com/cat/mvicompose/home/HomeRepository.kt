package com.cat.mvicompose.home

import kotlinx.coroutines.delay

class HomeRepository {

    // simulate to get data from server
    suspend fun getUsers(): List<User> {
        delay(2000)
        return listOf(User(), User(), User())
    }
}