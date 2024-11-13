package com.example.chatting.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.chatting.LoginActivity
import com.example.chatting.R
import com.example.chatting.databinding.FragmentMypageBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MyPageFragment : Fragment(R.layout.fragment_mypage) {
    private lateinit var binding: FragmentMypageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMypageBinding.bind(view)

        binding.applyButton.setOnClickListener {
            val userName = binding.userNameEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()

            if(userName.isEmpty() || description.isEmpty()) {
                Toast.makeText(context, "유저이름은 빈 값으로 설정할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
            
            //파이어베이스 데이터베이스 업데이트
        }

        binding.logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()
        }

    }
}