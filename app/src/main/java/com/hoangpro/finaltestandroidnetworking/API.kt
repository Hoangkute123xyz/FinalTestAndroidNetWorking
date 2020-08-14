package com.hoangpro.finaltestandroidnetworking

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
interface API{
    @GET("albums")
    fun getAlbumData(): Call<JsonElement>
}