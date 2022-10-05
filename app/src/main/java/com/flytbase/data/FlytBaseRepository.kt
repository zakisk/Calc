package com.flytbase.data

import com.flytbase.domain.models.History
import com.flytbase.domain.models.User
import com.google.firebase.database.DataSnapshot

class FlytBaseRepository(private val flytBaseService: FlytBaseService) {

    fun authenticate(user: User, onResponse: (DataSnapshot) -> Unit) {
        flytBaseService.authenticate(user, onResponse)
    }

    fun addHistory(history: History) {
        flytBaseService.addHistory(history)
    }

    fun getAllHistory(onResponse: (DataSnapshot) -> Unit) {
        flytBaseService.getAllHistory(onResponse)
    }

}