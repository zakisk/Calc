package com.flytbase.data

import com.flytbase.domain.models.History
import com.flytbase.domain.models.User
import com.google.firebase.database.DataSnapshot

interface FlytBaseService {

    fun authenticate(user: User, onResponse: (DataSnapshot) -> Unit)

    fun addHistory(history: History)

    fun getAllHistory(onResponse: (DataSnapshot) -> Unit)
}