package com.example.chatting.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chatting.R
import com.example.chatting.databinding.FragmentMypageBinding

class MyPageFragment : Fragment(R.layout.fragment_mypage) {
    private lateinit var binding: FragmentMypageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMypageBinding.bind(view)

        binding.applyButton.setOnClickListener {}
        binding.logoutButton.setOnClickListener {}
    }
}