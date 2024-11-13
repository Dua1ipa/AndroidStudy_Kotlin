package com.example.chatting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chatting.chatlist.ChatListFragment
import com.example.chatting.chatlist.ChatRoomItem
import com.example.chatting.databinding.ActivityMainBinding
import com.example.chatting.mypage.MyPageFragment
import com.example.chatting.userlist.UserFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userFragment = UserFragment()
    private val chatListFragment = ChatListFragment()
    private val myPageFragment = MyPageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val currentUser = Firebase.auth.currentUser
        if (currentUser == null) {  //해당 사용자가 없으면 로그인 화면으로 이동
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            binding.bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.userList -> {
                        replaceFragment(userFragment)
                        return@setOnItemSelectedListener true
                    }

                    R.id.chatroomList -> {
                        replaceFragment(chatListFragment)
                        return@setOnItemSelectedListener true
                    }

                    R.id.myPage -> {
                        replaceFragment(myPageFragment)
                        return@setOnItemSelectedListener true
                    }

                    else -> {
                        return@setOnItemSelectedListener false
                    }
                }
            }
            replaceFragment(userFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }
}