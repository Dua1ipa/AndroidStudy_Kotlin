package com.example.chatting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.chatting.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    companion object {  //자바 static 키워드와 같음
        private const val TAG = "LoginActivity"
    }

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        // 로그인 버튼 //
        binding.loginButton.setOnClickListener {
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
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        binding.idTextInputEditText.setText("")   //빈칸으로 만들기
                        binding.idTextInputEditText.clearFocus()  //포커스 해제
                        binding.pwTextInputEditText.setText("")
                        binding.pwTextInputEditText.clearFocus()
                        // 키보드 숨기기 //
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(binding.idTextInputEditText.windowToken, 0)
                    }
                }
        }

        // 회원가입 이동 버튼 //
        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // 아이디&비번 찾기 이동 버튼 //
        binding.findButton.setOnClickListener { }
    }

}