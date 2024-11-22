package com.example.kakaologin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.kakaologin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "LoginActivity"
    }
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kakaoLoginImageButton.setOnClickListener {

        }

    }
}