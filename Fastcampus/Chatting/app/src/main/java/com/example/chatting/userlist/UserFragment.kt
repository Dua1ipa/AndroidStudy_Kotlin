package com.example.chatting.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.Key.Companion.DB_USERS
import com.example.chatting.R
import com.example.chatting.databinding.FragmentUserlistBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class UserFragment : Fragment(R.layout.fragment_userlist) {
    private lateinit var binding: FragmentUserlistBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserlistBinding.bind(view)

        val userListAdapter = UserAdapter()
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