package com.example.phoneverify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PinViewModel : ViewModel() {
    val passwordLiveData: LiveData<CharSequence> by lazy { _passwordLiveData }
    private val _passwordLiveData by lazy { MutableLiveData<CharSequence>() }

    private val password: StringBuffer = StringBuffer("")
    fun inputNumber(num: String) {
        if (password.length < 6) {
            password.append(num)
            _passwordLiveData.value = password.toString()
        }
    }
    fun deleteNumber() {
        if (password.isNotEmpty()) {
            password.deleteCharAt(password.length - 1)
            _passwordLiveData.value = password.toString()
        }
    }
    fun doneNumber() {
        password.replace(0, password.length, "")
    }
}