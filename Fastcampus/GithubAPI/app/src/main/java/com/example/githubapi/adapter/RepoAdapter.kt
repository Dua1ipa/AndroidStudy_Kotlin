package com.example.githubapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.adapter.UserAdapter.Companion.diffUtil
import com.example.githubapi.databinding.ItemRepoBinding
import com.example.githubapi.model.Repo

class RepoAdapter : ListAdapter<Repo, RepoAdapter.ViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Repo>() {  //아이템 변경 사항을 효율적으로 감지
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id  //두 항목이 동일한지 확인
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem  //두 항목의 내용이 동일한지 확인
            }
        }
    }

    // RecyclerView의 개별 아이템을 표시하기 위해 사용 //
    inner class ViewHolder(private val viewBinding: ItemRepoBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: Repo) {  //각 리스트 항목의 데이터를 View에 바인딩
            viewBinding.repoNameTextView.text = item.name
            viewBinding.descriptionTextView.text = item.description
            viewBinding.starCountTextView.text = "${item.starCount}"
            viewBinding.forkCountTextView.text = "${item.forkCount}"
        }

    }

    // ViewHolder를 처음 생성할 때 호출 //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapter.ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // ViewHolder에 데이터를 바인딩하는 메서드 //
    override fun onBindViewHolder(holder: RepoAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}