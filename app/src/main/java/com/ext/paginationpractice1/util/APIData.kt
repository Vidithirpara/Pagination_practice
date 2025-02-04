package com.ext.paginationpractice1.util

import com.google.gson.annotations.SerializedName

data class Pagination1(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

data class UserResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>,
    val support: Support
)

data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

data class Support(
    val url: String,
    val text: String
)

