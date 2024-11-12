package com.example.chatting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.chatting.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "RegisterActivity"
    }
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        // 회원가입 버튼 //
        binding.registerButton.setOnClickListener {
            val email = binding.idTextInputEditText.text.toString()
            val password = binding.pwTextInputEditText.text.toString()
            if (email.isEmpty()) {
                binding.idTextInputEditText.error = "이메일을 입력해주세요."
                binding.idTextInputEditText.requestFocus()
                return@setOnClickListener  //이벤트 핸들러 종료 (setOnClickListener 블록에서 즉시 빠져나오게 된다)
            } else if (password.isEmpty()) {
                binding.pwTextInputEditText.error = "비밀번호를 입력해주세요."
                binding.pwTextInputEditText.requestFocus()
                return@setOnClickListener  //이벤트 핸들러 종료 (setOnClickListener 블록에서 즉시 빠져나오게 된다)
            }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {  //회원가입 성공
                        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT,).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {  //회원가입 실패
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT,).show()
                    }
                }
        }
    }

}