package com.example.chatting.chatlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.Key.Companion.DB_CHAT_ROOMS
import com.example.chatting.R
import com.example.chatting.databinding.FragmentChatlistBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ChatListFragment : Fragment(R.layout.fragment_chatlist) {
    private lateinit var binding: FragmentChatlistBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatlistBinding.bind(view)

        val chatListAdapter = ChatListAdapter()
        binding.chatListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatListAdapter
        }

        // 데이터 조회 //
        val currentUser = Firebase.auth.currentUser ?: return
        val chatRoomsDB = Firebase.database.reference.child(DB_CHAT_ROOMS).child(currentUser.uid)

        chatRoomsDB.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val chatRoomList = snapshot.children.map {
                    it.getValue(ChatRoomItem::class.java)
                }
                chatListAdapter.submitList(chatRoomList)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}