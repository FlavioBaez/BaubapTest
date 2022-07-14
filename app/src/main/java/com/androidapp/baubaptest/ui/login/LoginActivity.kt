package com.androidapp.baubaptest.ui.login


import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.androidapp.baubaptest.R
import com.androidapp.baubaptest.databinding.ActivityLoginBinding
import com.androidapp.baubaptest.ui.utils.createSnackBarWithError
import com.androidapp.baubaptest.ui.utils.hideKeyboard


class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            if (loginResult is LoginResult.Success) {
                onLoginSuccess(loginResult.data)
            }
            if (loginResult is LoginResult.Error) {
                onLoginFailed(loginResult.error)
            }
            if (loginResult is LoginResult.ErrorEx) {
                onLoginFailed(loginResult.ex.localizedMessage)
            }
        })

        binding.password.apply {

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            binding.username.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }

            binding.login.setOnClickListener {
                hideKeyboard()
                loginViewModel.login(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }
        }
    }

    private fun onLoginSuccess(message: String) {
        createSnackBarWithError(binding.root, false, message)
    }

    private fun onLoginFailed(errorString: String?) {
        createSnackBarWithError(
            binding.root, true, if (errorString.isNullOrEmpty()) resources.getString(
                R.string.login_failed
            ) else errorString
        )
    }
}

