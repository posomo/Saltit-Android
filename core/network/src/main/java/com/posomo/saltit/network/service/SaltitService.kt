package com.posomo.saltit.network.service

import com.posomo.saltit.network.model.SaltitResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SaltitService {
    @GET("albums/{id}")
    fun getAlbum(@Path("id") id: Int): Call<SaltitResponse>
}