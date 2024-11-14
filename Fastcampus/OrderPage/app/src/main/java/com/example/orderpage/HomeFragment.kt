package com.example.orderpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.orderpage.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val homeData = context?.readData() ?: return

        // %s님, 오늘도 커피 한잔 어떠세요?
        binding.appBarTextView.text = getString(R.string.appbar_title_text, homeData.user.nickName)  //%s를 닉네임으로 채워줌
        binding.starCountTextView.text = getString(R.string.appbar_star_title, homeData.user.starCount, homeData.user.totalCount)

        binding.progressBar.progress = homeData.user.starCount
        binding.progressBar.max = homeData.user.totalCount

        Glide.with(binding.appBarImageView)
            .load(homeData.appBarImage)
            .into(binding.appBarImageView)
    }
}