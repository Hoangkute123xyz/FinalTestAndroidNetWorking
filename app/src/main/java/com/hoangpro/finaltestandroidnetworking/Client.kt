package com.hoangpro.finaltestandroidnetworking

import com.google.gson.Gson
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    companion object{
        private fun createAPI():API{
            val builder = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return builder.create(API::class.java)
        }

        fun getAllAlbums(onSuccess:(ArrayList<Album>?)->Unit, onFailure:()->Unit){
            createAPI().getAlbumData().enqueue(object :Callback<JsonElement>{
                override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    onFailure()
                }

                override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                    if (response.isSuccessful){
                        val res = response.body()?:"[]"
                        val arrAlbum = Gson().fromJson(res.toString(),Array<Album>::class.java)
                        val albumList = ArrayList<Album>()
                        albumList.addAll(arrAlbum)
                        onSuccess(albumList)
                    } else {
                        onFailure()
                    }
                }

            })
        }
    }
}