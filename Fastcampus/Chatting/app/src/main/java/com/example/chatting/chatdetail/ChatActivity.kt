package com.example.chatting.chatdetail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.Key.Companion.DB_CHATS
import com.example.chatting.Key.Companion.DB_USERS
import com.example.chatting.databinding.ActivityChatdetailBinding
import com.example.chatting.userlist.UserItem
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.database

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatdetailBinding

    private var chatRoomID: String = ""
    private var otherUserID: String = ""
    private var myUserID: String = ""

    private val chatItemList = mutableListOf<ChatItem>()

    /*

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatRoomID = intent.getStringExtra("chatRoomID") ?: return
        otherUserID = intent.getStringExtra("otherUserID") ?: return
        myUserID = Firebase.auth.currentUser?.uid ?: ""
        val chatAdapter = ChatAdapter()

        Firebase.database.reference.child(DB_USERS).child(myUserID).get()
            .addOnSuccessListener {
                val myUserItem = it.getValue(UserItem::class.java)
                val myUserName = myUserItem?.userName
            }
        Firebase.database.reference.child(DB_USERS).child(otherUserID).get()
            .addOnSuccessListener {
                val otherUserItem = it.getValue(UserItem::class.java)
                chatAdapter.otherUserItem = otherUserItem
            }

        Firebase.database.reference.child(DB_CHATS).child(chatRoomID)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val chatItem = snapshot.getValue(ChatItem::class.java)
                    chatItem ?: return

                    chatItemList.add(chatItem)
                    chatAdapter.submitList(chatItemList)
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildRemoved(snapshot: DataSnapshot) {}

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onCancelled(error: DatabaseError) {}
            })

        binding.chatRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ChatAdapter()
        }

        binding.sendButton.setOnClickListener {}
    }

}