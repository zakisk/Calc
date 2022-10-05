package com.flytbase.data

import com.flytbase.domain.models.History
import com.flytbase.domain.models.User
import com.flytbase.util.Constants.URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FlytBaseServiceImpl(private val mobile: String) : FlytBaseService {

    private var reference = FirebaseDatabase.getInstance(URL).reference

    override fun authenticate(user: User, onResponse: (DataSnapshot) -> Unit) {
        reference.child("users").child(user.mobile)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    onResponse(snapshot)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    override fun addHistory(history: History) {
        reference.child(mobile).child("history").push().setValue(history)
    }

    override fun getAllHistory(onResponse: (DataSnapshot) -> Unit) {
        reference.child(mobile).child("history").limitToLast(10)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    onResponse(snapshot)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
}