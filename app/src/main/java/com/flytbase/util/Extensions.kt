package com.flytbase.util

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


fun <T> MutableState<T>.postValue(newValue: T) {
    this.value = newValue
}

fun <T> SnapshotStateList<T>.postValue(newValue: List<T>) {
    this.addAll(newValue)
}

fun Context.showToast(message: String) {
    MainScope().launch {
        Toast.makeText(this@showToast, message, Toast.LENGTH_LONG).show()
    }
}