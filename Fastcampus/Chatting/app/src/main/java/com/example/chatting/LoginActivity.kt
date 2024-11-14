package com.example.chatting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.chatting.Key.Companion.DB_URL
import com.example.chatting.Key.Companion.DB_USERS
import com.example.chatting.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.database

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

        // 자동 로그인 확인
        val sharedPreferences = getSharedPreferences("AutoLogin", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        // 자동 로그인 정보 확인 //
        if(isLoggedIn){  //자동 로그인 체크 되있었다면
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
        }

        // 아이디 입력란에서 Enter 키를 누르면 비밀번호 입력란으로 이동
        binding.idTextInputEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.pwTextInputEditText.requestFocus()  //비밀번호 필드로 포커스 이동
                true
            } else { false }
        }

        // 비밀번호 입력란에서 Enter 키를 누르면 로그인 처리를 실행
        binding.pwTextInputEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // InputMethodManager를 통해 키보드를 닫음
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.pwTextInputEditText.windowToken, 0)
                true
            } else { false }
        }

        // 로그인 버튼 //
        binding.loginButton.setOnClickListener {
            hideKeyboard()  //소프트 키보드를 숨기고 로그인 처리 로직을 실행
            val email = binding.idTextInputEditText.text.toString()
            val password = binding.pwTextInputEditText.text.toString()
            if (email.isEmpty()) {
                binding.idTextInputEditText.error = "이메일을 입력해주세요."
                binding.idTextInputEditText.requestFocus()
                return@setOnClickListener  //이벤트 핸들러 종료 (setOnClickListener 블록에서 즉시 빠져나오게 된다)
            } else if (password.isEmpty()) {
                binding.pwTextInputEditText.error = "비밀번호를 입력해주세요."
                binding.pwTextInputEditText.requestFocus()
                return@setOnClickListener   //이벤트 핸들러 종료 (setOnClickListener 블록에서 즉시 빠져나오게 된다)
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    val currentUser = auth.currentUser
                    if (task.isSuccessful && currentUser != null) {  //로그인 성공하면

                        // 로그인 할때 로그인 정보 업데이트 //
                        val user = mutableMapOf<String, Any>()
                        user["userID"] = currentUser.uid
                        user["userName"] = email

                        Firebase.database(DB_URL).reference.child(DB_USERS).child(currentUser.uid)
                            .updateChildren(user)

                        // 자동 로그인 체크 확인 //
                        if (binding.loginCheckBox.isChecked){
                            // 로그인 상태 저장하기
                            sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
                            sharedPreferences.edit().apply()
                        }

                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                        Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    } else {  //로그인 실패하면
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        binding.idTextInputEditText.setText("")   //빈칸으로 만들기
                        binding.idTextInputEditText.clearFocus()  //포커스 해제
                        binding.pwTextInputEditText.setText("")
                        binding.pwTextInputEditText.clearFocus()
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

    // 키보드를 숨기는 함수 //
    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}