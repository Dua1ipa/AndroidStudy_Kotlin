package fastcampus.part1.chapter4

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import fastcampus.part1.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goEditActivityButton.setOnClickListener {
            startActivity(intent.setClass(this, EditActivity::class.java)) 
        }

        binding.deleteButton.setOnClickListener {
            deleteData()
        }
    }

    private fun deleteData(){
        with(getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).edit()){
            clear()
            apply()
            getDataUiUpdate()
        }
        Toast.makeText(this, "초기화를 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        getDataUiUpdate()
    }

    private fun getDataUiUpdate(){
        with(getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)){
            binding.nameValueEditText.text = getString(NAME, "미정")
            binding.birthValueTextView.text = getString(BIRTH, "미정")
            binding.bloodValueEditText.text = getString(BLOOD, "미정")
            binding.contactValueTextView.text = getString(PHONE, "미정")

            val caution = getString(CAUTION, "")

            binding.cautionTextView.isVisible = caution.isNullOrEmpty().not()
            binding.cautionCheckBox.isVisible = caution.isNullOrEmpty().not()
            binding.cautionValueTextView.isVisible = caution.isNullOrEmpty().not()

            if(!caution.isNullOrEmpty()){
                binding.cautionValueTextView.text = caution
            }
        }
    }

}