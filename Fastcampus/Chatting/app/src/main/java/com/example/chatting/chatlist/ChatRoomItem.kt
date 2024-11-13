package com.example.chatting.chatlist

data class ChatRoomItem(
    val chatRoomID: String ?= null,
    val otherUserName: String ?= null,
    val lastMessage: String ?= null,
    val otherUserID: String ?= null
)
