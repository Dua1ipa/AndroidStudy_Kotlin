package com.example.orderpage

import android.animation.ValueAnimator
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

        val homeData = context?.readData("home.json", Home::class.java) ?: return
        val menuData = context?.readData("menu.json", Menu::class.java) ?: return

        initAppBar(homeData) // 앱바 부분 //

        initRecommendMenu(homeData, menuData) // 메뉴 부분 //

        initBanner(homeData) // 배너 부분 //

        initFood(menuData) // Food 부분 //

        binding.scrollView.setOnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
            if(scrollY == 0)
                binding.floatingActionButton.extend()
            else
                binding.floatingActionButton.shrink()
        }
    }

    private fun initFood(menuData: Menu) {
        menuData.food.forEach { menuItem ->
            binding.foodLayout.menuLinearLayout.addView(
                MenuView(context = requireContext()).apply {
                    setTitle(menuItem.name)
                    setImageView(menuItem.image)
                }
            )
        }
    }

    private fun initBanner(homeData: Home) {
        // 배너 부분 //
        binding.bannerLayout.bannerImageView.apply {
            Glide.with(this)
                .load(homeData.banner.image)
                .into(this)
            this.contentDescription = homeData.banner.contentDescription
        }

        binding.recommendMenuList.titleTextView.text =
            getString(R.string.foodmenu_title)
    }

    private fun initRecommendMenu(homeData: Home, menuData: Menu) {
        binding.recommendMenuList.titleTextView.text =
            getString(R.string.recommend_title, homeData.user.nickName)

        // 메뉴 추천 부분 //
        menuData.coffee.forEach { menuItem ->
            binding.recommendMenuList.menuLinearLayout.addView(
                MenuView(context = requireContext()).apply {
                    setTitle(menuItem.name)
                    setImageView(menuItem.image)
                }
            )
        }
    }

    private fun initAppBar(homeData: Home) {
        // %s님, 오늘도 커피 한잔 어떠세요?
        binding.appBarTextView.text =
            getString(R.string.appbar_title_text, homeData.user.nickName)  //%s를 닉네임으로 채워줌
        binding.starCountTextView.text =
            getString(R.string.appbar_star_title, homeData.user.starCount, homeData.user.totalCount)

        binding.progressBar.max = homeData.user.totalCount

        Glide.with(binding.appBarImageView)
            .load(homeData.appBarImage)
            .into(binding.appBarImageView)

        //프로그래스바 애니메이션 설정
        ValueAnimator.ofInt(0, homeData.user.starCount).apply {
            duration = 1000
            addUpdateListener {
                binding.progressBar.progress = it.animatedValue as Int
            }
            start()
        }
    }

}