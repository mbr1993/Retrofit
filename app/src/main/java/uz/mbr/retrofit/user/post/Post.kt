package uz.mbr.retrofit.user.post

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)