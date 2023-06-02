package com.example.composebasic.utils

data class RandomUser(
    val name: String = "개발하는 정대리 🙉",
    val description: String = "안녕하세요.",
    val profileImage: String = "https://plus.unsplash.com/premium_photo-1685125884096-e726b9496baf?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80"
)

object DummyDataProvider {
    val userList = List<RandomUser>(200) { RandomUser() }
}