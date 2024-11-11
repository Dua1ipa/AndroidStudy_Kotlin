package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.findButton.setOnClickListener {
            val intent = Intent(this, FindActivity::class.java)
            startActivity(intent)
        }
    }

    // 유효성 검사 함수 //
    private fun loginCheck() {
        val id = binding.idTextInputEditText.text.toString()
        val pw = binding.pwTextInputEditText.text.toString()

        binding.idTextInputEditText.addTextChangedListener {
            it?.let { text ->
                binding.idTextInputLayout.error =
                    when (text.length) {
                        0 -> "아이디를 입력해주세요."
                        11 -> "아이디는 10자 이하로 입력해주세요."
                        else -> null
                    }
            }
        }

        binding.pwTextInputEditText.addTextChangedListener {
            it?.let { text ->
                binding.pwTextInputLayout.error =
                    when (text.length) {
                        0 -> "비밀번호를 입력해주세요."
                        11 -> "비밀번호는 10자 이하로 입력해주세요."
                        else -> null
                    }
            }
        }

        if (id.equals("00000000") && pw.equals("00000000")) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}
