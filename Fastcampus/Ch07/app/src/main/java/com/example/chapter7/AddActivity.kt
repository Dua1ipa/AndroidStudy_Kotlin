package com.example.chapter7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    private lateinit var binding: AddActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddActivity.inflate(layoutInflater)
    }

}