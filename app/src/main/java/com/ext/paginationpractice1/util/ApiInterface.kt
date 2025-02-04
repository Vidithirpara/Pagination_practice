package com.ext.paginationpractice1.util

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    fun getData(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): Call<List<Pagination1>>
}

interface PaginationApiWithImage {
    @GET("users")
    fun getUsers(
        @Query("page") page: Int
    ): Call<UserResponse>
}