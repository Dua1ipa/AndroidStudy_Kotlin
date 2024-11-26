package com.example.kakaologin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kakaologin.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User

class LoginActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "LoginActivity"
    }
    private lateinit var binding: ActivityLoginBinding
    private lateinit var emailLoginResult: ActivityResultLauncher<Intent>
    private lateinit var tempUser : User

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if(error != null){
            showErrorToast()
            //로그인 실패
            Log.e(TAG, "로그인 실패", error)
        }else if (token != null){
            //로그인 성공
            Log.e(TAG, "로그인 성공 ${token.accessToken}")
            getKakaoAccountInfo()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kakao SDK 초기화
        KakaoSdk.init(this, "8f784e972742973545a426dd4fa23882")

        emailLoginResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                val email = it.data?.getStringExtra("email")
                if(email == null){
                    showErrorToast()
                    return@registerForActivityResult
                }else{
                    signInFirebase(tempUser, email)
                }
            }
        }

        binding.kakaoLoginImageButton.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                //카카오톡이 설치 되었다면 (카카오톡 로그인)
                UserApiClient.instance.loginWithKakaoTalk(this){ token, error ->
                    if (error != null) {  //오류가 있다면
                        Log.e(TAG, "로그인 실패", error)
                        if(error is ClientError && error.reason == ClientErrorCause.Cancelled){
                            Log.e(TAG, "로그인 실패", error)
                            //사용자가 로그인 취소를 하면
                            return@loginWithKakaoTalk
                        }
                        //사용자가 로그인 취소를 하지 않았다면
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)  //다시 로그인 시도
                    }else if (token != null) {  //카카오톡 토큰이 존재하면
                        if(Firebase.auth.currentUser == null){
                            //카카오톡에서 정보를 가져와서 파이어베이스 로그인
                            getKakaoAccountInfo()
                        }else{
                            // 카카오톡 토큰이 존재하고 파이어베이스에 정보가 존재하면 -> 맵으로 이동
                            navigateToMapActivity()
                        }
                    }
                    else if (token != null) {
                        Log.e(TAG, "로그인 성공 ${token.accessToken}")
                        startActivity(Intent(this, MapActivity::class.java))
                    }
                }
            }else{
                //카카오톡이 설치 되있지 않다면 (카카오계정 로그인)
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    private fun showErrorToast(){
        Toast.makeText(this, "사용자 로그인에 실패", Toast.LENGTH_SHORT).show()
    }

    private fun getKakaoAccountInfo(){
        UserApiClient.instance.me { user, error ->
            if(error != null){
                showErrorToast()
            }else if (user != null){  //카카오 계정 정보가 존재하면
                // 사용자 정보 요청 성공
                checkKakaoUserData(user)
            }
        }
    }

    private fun checkKakaoUserData(user : User){
        val kakaoEmail = user.kakaoAccount?.email.orEmpty()
        if(kakaoEmail.isEmpty()){  //카카오 이메일이 없으면
            tempUser = user
            // 추가로 이메일을 받는 작업
            emailLoginResult.launch(Intent(this, EmailLoginActivity::class.java))

            return
        }
        signInFirebase(user, kakaoEmail)
    }

    private fun signInFirebase(user: User, kakaoEmail: String){
        val UID = user.id.toString()

        Firebase.auth.createUserWithEmailAndPassword(kakaoEmail, UID)
            .addOnCompleteListener {
                if(it.isSuccessful){  //파이어베이스 계정 생성 성공
                    updateFirebaseDB(user)
                }else{
                    showErrorToast()
                }
            }.addOnFailureListener {  //파이어베이스 계정 생성 실패
                if(it is FirebaseAuthUserCollisionException){  // 이미 가입된 계정
                    Firebase.auth.signInWithEmailAndPassword(kakaoEmail, UID).addOnCompleteListener { result ->
                        if(result.isSuccessful){  // 파이어베이스 로그인 성공
                            updateFirebaseDB(user)
                        }else{  // 파이어베이스 로그인 실패
                            showErrorToast()
                        }
                    }.addOnFailureListener { error ->
                        error.printStackTrace()
                        showErrorToast()
                    }
                }else{
                    showErrorToast()
                }
            }
    }

    private fun updateFirebaseDB(user: User){
        val UID = Firebase.auth.currentUser?.uid.orEmpty()

        val userMap = mutableMapOf<String, Any>()
        userMap["UID"] = UID
        userMap["name"] = user.kakaoAccount?.profile?.nickname.orEmpty()
        userMap["profilePhoto"] = user.kakaoAccount?.profile?.thumbnailImageUrl.orEmpty()

        Firebase.database.reference.child("Person").child(UID).updateChildren(userMap)
        navigateToMapActivity()
    }

    private fun navigateToMapActivity(){
        startActivity(Intent(this, MapActivity::class.java))
    }
}