package com.example.phoneverify.BindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.phoneverify.R

@BindingAdapter(value = ["code_text", "code_index"])
fun ImageView.setPin(codeText: CharSequence?, index: Int) {
    if(codeText != null){
        if(codeText.length > index){
            this.setImageResource(R.drawable.baseline_circle_black_24)
        }else{
            this.setImageResource(R.drawable.baseline_circle_gray_24)
        }
    }
}