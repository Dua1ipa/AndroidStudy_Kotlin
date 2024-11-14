package com.example.chatting.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatting.databinding.ItemUserBinding

/*
    1. UserItem 리스트를 받아 UI에 표시
    2. ViewHolder를 생성
    3. onBindViewHolder는 데이터를 UI에 연결
    4. DiffUtil.ItemCallback을 사용해 리스트의 변경 사항을 감지하고 효율적으로 업데이트
 */


class UserAdapter(private val onClick: (UserItem) -> Unit) : ListAdapter<UserItem, UserAdapter.ViewHolder>(differ) {
    // ItemUserBinding: 이 뷰 바인딩 객체는 item_user 레이아웃 XML 파일을 연결
    inner class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserItem) {  //bind 메서드: UserItem 객체의 데이터를 텍스트 뷰에 할당하는 역할
            binding.nicknameTextView.text = item.userName
            binding.descriptionTextView.text = item.description

            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    // RecyclerView가 새로운 뷰를 필요로 할 때 호출 (생성)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    // RecyclerView가 각 항목에 데이터를 바인딩할 때 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {  //Java의 static 멤버와 유사
        val differ = object : DiffUtil.ItemCallback<UserItem>() {  //리스트의 변경 사항을 감지할 때 사용하는 콜백
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {  //두 UserItem이 같은 항목인지 확인 (userID로 비교)
                return oldItem.userID == newItem.userID
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {  //userID가 같더라도 내용이 변경되었는지 확인
                return oldItem == newItem
            }
        }
    }
}