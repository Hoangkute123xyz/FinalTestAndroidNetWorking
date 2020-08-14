package com.hoangpro.finaltestandroidnetworking

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName

data class Album (
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String
    ) {
        companion object {
            fun create(json: String): Album {
                val gson = GsonBuilder().create()
                return gson.fromJson(json, Album::class.java)
            }
        }

        override fun toString(): String {
            val gson = GsonBuilder().create()
            return gson.toJson(this)
        }
    }
