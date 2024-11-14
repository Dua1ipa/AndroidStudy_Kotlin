package com.example.chatting.chatlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatting.databinding.ItemChatroomBinding
import com.example.chatting.databinding.ItemUserBinding
import com.example.chatting.userlist.UserItem

class ChatListAdapter(private val onClick: (ChatRoomItem) -> Unit) :
    ListAdapter<ChatRoomItem, ChatListAdapter.ViewHolder>(differ) {
    // ItemUserBinding: 이 뷰 바인딩 객체는 item_user 레이아웃 XML 파일을 연결
    inner class ViewHolder(private val binding: ItemChatroomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatRoomItem) {  //bind 메서드: UserItem 객체의 데이터를 텍스트 뷰에 할당하는 역할
            binding.nicknameTextView.text = item.otherUserName
            binding.lastMessageTextView.text = item.lastMessage

            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    // RecyclerView가 새로운 뷰를 필요로 할 때 호출 (생성)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemChatroomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // RecyclerView가 각 항목에 데이터를 바인딩할 때 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {  //Java의 static 멤버와 유사
        val differ = object : DiffUtil.ItemCallback<ChatRoomItem>() {  //리스트의 변경 사항을 감지할 때 사용하는 콜백
            override fun areItemsTheSame(
                oldItem: ChatRoomItem,
                newItem: ChatRoomItem
            ): Boolean {  //두 UserItem이 같은 항목인지 확인 (userID로 비교)
                return oldItem.chatRoomID == newItem.chatRoomID
            }

            override fun areContentsTheSame(
                oldItem: ChatRoomItem,
                newItem: ChatRoomItem
            ): Boolean {  //userID가 같더라도 내용이 변경되었는지 확인
                return oldItem == newItem
            }
        }
    }
}