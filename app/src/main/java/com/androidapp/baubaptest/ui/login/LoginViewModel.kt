package com.androidapp.baubaptest.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult<String>>()
    val loginResult: LiveData<LoginResult<String>> = _loginResult

    fun login(username: String, password: String) {
        if (isPasswordValid(password) && isUserNameValid(username)) {
            _loginResult.value = LoginResult.Success("Login successful")
        } else {
            _loginResult.value = LoginResult.Error("Login failed")
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}