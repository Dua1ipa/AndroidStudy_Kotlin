package com.example.chatting.chatdetail

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.Key
import com.example.chatting.Key.Companion.DB_CHATS
import com.example.chatting.Key.Companion.DB_CHAT_ROOMS
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
    companion object {
        const val TAG = "ChatActivity"
        const val EXTRA_CHAT_ROOM_ID = "CHAT_ROOM_ID"
        const val EXTRA_OTHER_USER_ID = "OTHER_USER_ID"
    }

    private lateinit var binding: ActivityChatdetailBinding

    private var chatRoomID: String = ""
    private var otherUserID: String = ""
    private var myUserID: String = ""
    private var myUserName: String = ""

    private val chatItemList = mutableListOf<ChatItem>()

    /*
        User : {
            유저A ID : {
                유저ID
                유저 이름
                설명
            }
            유저B ID : {
                유저ID
                유저 이름
                설명
            }
        }

        ChatRoom : {
            유저A : {
                유저B : {
                    채팅방ID
                    마지막 메시지
                    유저B ID
                    유저B 이름
                }
            }
            유저B : {
                유저A :{
                    채팅방ID
                    마지막 메시지
                    유저A ID
                    유저A 이름
                }
            }
       }

        Chat : {
            채팅방ID : {
                메세지ID : {
                    채팅ID
                    메시지 "A가 보냄"
                    유저A ID
                }
               메세지ID : {
                    채팅ID
                    메시지 : "B가 보냄"
                    유저B ID
                }
            }
        }
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatRoomID = intent.getStringExtra(EXTRA_CHAT_ROOM_ID) ?: return
        otherUserID = intent.getStringExtra(EXTRA_OTHER_USER_ID) ?: return
        myUserID = Firebase.auth.currentUser?.uid ?: ""
        val chatAdapter = ChatAdapter()

        Firebase.database.reference.child(DB_USERS).child(myUserID).get()
            .addOnSuccessListener {
                val myUserItem = it.getValue(UserItem::class.java)
                myUserName = myUserItem?.userName ?: ""
            }
        Firebase.database.reference.child(DB_USERS).child(otherUserID).get().addOnSuccessListener {
            val otherUserItem = it.getValue(UserItem::class.java)
            chatAdapter.otherUserItem = otherUserItem
        }

        Firebase.database.reference.child(DB_CHATS).child(chatRoomID)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val chatItem = snapshot.getValue(ChatItem::class.java)
                    chatItem ?: return

                    chatItemList.add(chatItem)
                    chatAdapter.submitList(chatItemList.toMutableList())  //리스트 복사 (갱신)
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildRemoved(snapshot: DataSnapshot) {}

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onCancelled(error: DatabaseError) {}
            })

        // 채팅 목록 RecyclerView 설정 //
        binding.chatRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ChatAdapter()
        }

        // 초기에는 버튼을 비활성화
        binding.sendButton.isEnabled = false

        // EditText에 TextWatcher 추가
        binding.messageEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.sendButton.isEnabled = !s.isNullOrEmpty()  //EditText가 비어 있는지 확인하여 버튼 상태 설정
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        // 채팅 메시지 전송 버튼 //
        binding.sendButton.setOnClickListener {
            Log.d(TAG, "전송 버튼 눌림")
            val message = binding.messageEditText.text.toString()
            // 채팅 데이터 생성 //
            val newChatItem = ChatItem(
                message = message.toString(),
                userID = myUserID
            )
            Firebase.database.reference.child(DB_CHATS).child(chatRoomID).push().apply {
                newChatItem.chatID = key
                setValue(newChatItem)
            }

            val updates: MutableMap<String, Any> = hashMapOf (
                "$DB_CHAT_ROOMS/$myUserID/$otherUserID/lastMessage" to message,
                "$DB_CHAT_ROOMS/$otherUserID/$myUserID/lastMessage" to message,
                "$DB_CHAT_ROOMS/$otherUserID/$myUserID/chatRoomID" to chatRoomID,
                "$DB_CHAT_ROOMS/$otherUserID/$myUserID/otherUserID" to otherUserID,
                "$DB_CHAT_ROOMS/$otherUserID/$myUserID/otherUserName" to myUserName,
            )

            Firebase.database.reference.updateChildren(updates)

            binding.messageEditText.text.clear()
        }
    }

}