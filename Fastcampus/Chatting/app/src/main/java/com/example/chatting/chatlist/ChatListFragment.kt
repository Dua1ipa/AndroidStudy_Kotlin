package com.example.chatting.chatlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.R
import com.example.chatting.databinding.FragmentChatlistBinding

class ChatListFragment : Fragment(R.layout.fragment_chatlist){
    private lateinit var binding: FragmentChatlistBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatlistBinding.bind(view)

        val chatListAdapter = ChatListAdapter()
        binding.chatListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatListAdapter
        }

        chatListAdapter.submitList(
            mutableListOf<ChatRoomItem>().apply {
                add(ChatRoomItem("11", "22", "33"))
            }
        )
    }

}