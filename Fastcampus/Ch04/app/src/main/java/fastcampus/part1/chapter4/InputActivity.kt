package fastcampus.part1.chapter4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fastcampus.part1.chapter4.databinding.InputActivityBinding

class InputActivity : AppCompatActivity(){
    private lateinit var binding: InputActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InputActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("intentMessage") ?: "없음"
        Log.d("InputActivity", message) 
    }

}