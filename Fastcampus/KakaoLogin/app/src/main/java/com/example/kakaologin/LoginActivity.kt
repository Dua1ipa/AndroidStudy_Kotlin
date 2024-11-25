package com.example.kakaologin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.kakaologin.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "LoginActivity"
    }
    private lateinit var binding: ActivityLoginBinding
    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if(error != null){
            //로그인 실패
            Log.e(TAG, "로그인 실패", error)
        }else if (token != null){
            //로그인 성공
            Log.e(TAG, "로그인 성공 ${token.accessToken}")
            startActivity(Intent(this, MapActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kakao SDK 초기화
        KakaoSdk.init(this, "8f784e972742973545a426dd4fa23882")

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
}