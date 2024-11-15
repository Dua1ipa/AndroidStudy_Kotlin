package com.example.chatting.mypage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.example.chatting.Key.Companion.DB_USERS
import com.example.chatting.LoginActivity
import com.example.chatting.R
import com.example.chatting.databinding.FragmentMypageBinding
import com.example.chatting.userlist.UserItem
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database

class MyPageFragment : Fragment(R.layout.fragment_mypage) {
    private lateinit var binding: FragmentMypageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMypageBinding.bind(view)

        val currentUserID = Firebase.auth.currentUser?.uid ?: ""
        val currentUserDB = Firebase.database.reference.child(DB_USERS).child(currentUserID)

        // 현재 유저 정보 가져오기 //
        currentUserDB.get().addOnSuccessListener {
            val currentUserItem = it.getValue(UserItem::class.java) ?: return@addOnSuccessListener
            binding.userNameEditText.setText(currentUserItem.userName)
            binding.descriptionEditText.setText(currentUserItem.description)
        }

        // 적용하기 버튼 //
        binding.applyButton.setOnClickListener {
            val userName = binding.userNameEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()

            if (userName.isEmpty() || description.isEmpty()) {
                Toast.makeText(context, "유저이름은 빈 값으로 설정할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }

            val user = mutableMapOf<String, Any>()
            user["userName"] = userName
            user["description"] = description
            currentUserDB.updateChildren(user)
        }

        // 로그아웃 버튼 //
        binding.logoutButton.setOnClickListener {
            //SharedPreferences에서 로그인 상태를 false로 변경
            val sharedPreferences = requireActivity().getSharedPreferences("AutoLogin", Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()

            Firebase.auth.signOut()  //파이어베이스 로그아웃
            startActivity(Intent(context, LoginActivity::class.java))  //LoginActivity로 이동
            Toast.makeText(requireContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            activity?.finish()  // Activity 종료
        }

    }
}