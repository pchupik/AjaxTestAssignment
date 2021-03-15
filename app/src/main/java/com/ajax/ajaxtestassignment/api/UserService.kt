package com.ajax.ajaxtestassignment.api

import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("api")
    suspend fun users(@Query("results") number : Int) : Response
}