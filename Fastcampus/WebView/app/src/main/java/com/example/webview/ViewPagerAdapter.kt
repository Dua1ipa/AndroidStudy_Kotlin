package com.example.webview

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity) :
    FragmentStateAdapter(mainActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return WebViewFragment()
            1 -> return WebViewFragment()
            else -> return WebViewFragment()
        }
    }
}