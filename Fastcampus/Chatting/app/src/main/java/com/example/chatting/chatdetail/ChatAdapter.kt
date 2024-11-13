package com.example.chatting.chatdetail

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatting.chatlist.ChatRoomItem
import com.example.chatting.databinding.ItemChatBinding
import com.example.chatting.databinding.ItemChatroomBinding
import com.example.chatting.databinding.ItemUserBinding
import com.example.chatting.userlist.UserItem

class ChatAdapter : ListAdapter<ChatItem, ChatAdapter.ViewHolder>(differ) {

    var otherUserItem: UserItem? = null

    // ItemUserBinding: 이 뷰 바인딩 객체는 item_user 레이아웃 XML 파일을 연결
    inner class ViewHolder(private val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatItem) {  //bind 메서드: UserItem 객체의 데이터를 텍스트 뷰에 할당하는 역할
            if (item.userID == otherUserItem?.userID) {  //다른 사람이 보낸
                binding.userNameTextView.isVisible = true
                binding.userNameTextView.text = otherUserItem?.userName
                binding.messageTextView.text = item.message
                binding.messageTextView.gravity = Gravity.START
            } else {  //내가 보낸
                binding.userNameTextView.isVisible = false
                binding.messageTextView.text = item.message
                binding.messageTextView.gravity = Gravity.END
            }
        }
    }

    // RecyclerView가 새로운 뷰를 필요로 할 때 호출 (생성)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemChatBinding.inflate(
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
        val differ = object : DiffUtil.ItemCallback<ChatItem>() {  //리스트의 변경 사항을 감지할 때 사용하는 콜백
            override fun areItemsTheSame(
                oldItem: ChatItem,
                newItem: ChatItem
            ): Boolean {  //두 UserItem이 같은 항목인지 확인 (userID로 비교)
                return oldItem.chatID == newItem.chatID
            }

            override fun areContentsTheSame(
                oldItem: ChatItem,
                newItem: ChatItem
            ): Boolean {  //userID가 같더라도 내용이 변경되었는지 확인
                return oldItem == newItem
            }
        }
    }
}