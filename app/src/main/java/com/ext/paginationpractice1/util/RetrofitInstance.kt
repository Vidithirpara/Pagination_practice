package com.ext.paginationpractice1.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PaginationAPI {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

object PaginationAPIWithImages {
    private const val BASE_URL = "https://reqres.in/api/"

    val api2: PaginationApiWithImage by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PaginationApiWithImage::class.java)
    }
}