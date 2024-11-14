package com.example.chatting.userlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.Key.Companion.DB_CHAT_ROOMS
import com.example.chatting.Key.Companion.DB_USERS
import com.example.chatting.R
import com.example.chatting.chatdetail.ChatActivity
import com.example.chatting.chatlist.ChatRoomItem
import com.example.chatting.databinding.FragmentUserlistBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import java.util.UUID

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
        유저A ID : {
            유저B ID : {
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
                메시지
                유저A
                유저B
            }
        }
    }
 */

class UserFragment : Fragment(R.layout.fragment_userlist) {
    private lateinit var binding: FragmentUserlistBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserlistBinding.bind(view)

        val userListAdapter = UserAdapter { otherUser ->
            val myUserID = Firebase.auth.currentUser?.uid ?: ""
            val chatRoomDB = Firebase.database.reference.child(DB_CHAT_ROOMS).child(myUserID)
                .child(otherUser.userID ?: "")
            // 다른 사람과 채팅방이 존재하는지 확인
            var chatRoomID = ""
            chatRoomDB.get().addOnSuccessListener {  //한번만 가져오기
                if (it.value != null) {  //채팅방이 존재하면
                    val chatRoom = it.getValue(ChatRoomItem::class.java)
                    chatRoomID = chatRoom?.chatRoomID ?: ""
                } else {  //채팅방 만들기
                    chatRoomID = UUID.randomUUID().toString()  //랜덤으로 uid 생성
                    //채팅방 데이터 생성
                    val newChatRoom = ChatRoomItem(
                        chatRoomID,
                        otherUserName =  otherUser.userName,
                        otherUserID = otherUser.userID
                    )
                    chatRoomDB.setValue(newChatRoom)  //새로운 채팅방 삽입
                }

                val intent = Intent(context, ChatActivity::class.java)
                intent.putExtra(ChatActivity.EXTRA_CHAT_ROOM_ID, chatRoomID)
                intent.putExtra(ChatActivity.EXTRA_OTHER_USER_ID, otherUser.userID)
                startActivity(intent)
            }

            "otherUserID"
            "chatRoomID"
        }
        binding.userRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }
        val currentUser = Firebase.auth.currentUser
        // 데이터 조회 //
        Firebase.database.reference.child(DB_USERS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userItemList = mutableListOf<UserItem>()
                    snapshot.children.forEach {
                        val user = it.getValue(UserItem::class.java)
                        user ?: return
                        if (user?.userID != currentUser?.uid) {
                            userItemList.add(user)
                        }
                    }
                    userListAdapter.submitList(userItemList)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }

}