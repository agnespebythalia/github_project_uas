package com.github.api.data

data class DetailUserResponse (
    val login : String,
    val avatar_url : String,
    val followers_url : String,
    val followers : Int,
    val following_url : String,
    val following : Int,
)