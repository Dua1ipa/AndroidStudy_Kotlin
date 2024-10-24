package com.example.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.login.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            loginCheck()
        }
        binding.registerButton.setOnClickListener {
            Intent(this, RegisterActivity::class.java).let {

            }
        }
        binding.findButton.setOnClickListener {

        }
    }

    private fun loginCheck() {
        val ID = binding.idTextInputEditText.text.toString()
        val PW = binding.pwTextInputEditText.text.toString()

        binding.idTextInputEditText.addTextChangedListener {
            it?.let { text ->
                binding.idTextInputLayout.error = when (text.length) {
                    0 -> "아이디를 입력해주세요."
                    11 -> "아이디는 10자 이하로 입력해주세요."
                    else -> null
                }
            }
        }

        binding.pwTextInputEditText.addTextChangedListener {
            it?.let { text ->
                binding.pwTextInputLayout.error = when (text.length) {
                    0 -> "비밀번호를 입력해주세요."
                    11 -> "비밀번호는 10자 이하로 입력해주세요."
                    else -> null
                }
            }
        }
    }

}