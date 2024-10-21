package fastcampus.part1.chapter4

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import fastcampus.part1.chapter4.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            saveData()
            finish()
        }

        binding.bloodSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )

        binding.birthLayer.setOnClickListener{
            val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                binding.birthTextView.text = "$year-${month + 1}-$dayOfMonth"
            }
            DatePickerDialog(
                this,
                listener,
                2000,
                1,
                1
            ).show()
        }

        binding.cautionCheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.cautionEditText.isVisible = isChecked
        }

        binding.cautionEditText.isVisible = binding.cautionCheckBox.isChecked

    }

    private fun saveData(){
        with(getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).edit()){
            putString(NAME, binding.nameValueEditText.text.toString())
            putString(BLOOD, getBloodType())
            putString(PHONE, binding.contactValueEditText.text.toString())
            putString(BIRTH, binding.birthTextView.text.toString())
            putString(CAUTION, getCaution())
            apply()
        }

        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getBloodType(): String{
        val bloodType = binding.bloodSpinner.selectedItem.toString()
        val bloodSign = if(binding.bloodTypePlus.isChecked) "+" else "-"
        return "$bloodSign$bloodType"
    }

    private fun getCaution(): String{
        return if(binding.cautionCheckBox.isChecked) binding.contactValueEditText.text.toString() else " "
    }
}