package com.example.orderpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orderpage.databinding.FragmentOrderBinding

class OrderFragment : Fragment(R.layout.fragment_order) {
    private lateinit var binding: FragmentOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderBinding.bind(view)

        val menuData = context?.readData("menu.json", Menu::class.java) ?: return
        val menuAdapter = MenuAdapter().apply {
            submitList(menuData.coffee)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = menuAdapter
        }

        binding.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            appBarLayout.totalScrollRange
        }
    }

}