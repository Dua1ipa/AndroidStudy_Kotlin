package fastcampus.part1.chapter4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fastcampus.part1.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goInputActivityButton.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            intent.putExtra("intentMessage", "응급의료정보")
            startActivity(intent)

        }

    }
}