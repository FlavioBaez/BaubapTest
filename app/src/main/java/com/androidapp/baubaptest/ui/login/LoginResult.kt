package com.androidapp.baubaptest.ui.login

sealed class LoginResult<out T> {
    class Success(val data: String) : LoginResult<String>()
    class Error(val error: String) : LoginResult<Nothing>()
    class ErrorEx(val ex: Exception) : LoginResult<Nothing>()
}
