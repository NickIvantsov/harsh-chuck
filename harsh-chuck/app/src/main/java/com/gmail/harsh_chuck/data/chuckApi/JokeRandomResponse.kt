package com.gmail.harsh_chuck.data.chuckApi


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JokeRandomResponse(
    @SerializedName("categories")
    @Expose
    val categories: List<Any> = listOf(),
    @SerializedName("created_at")
    @Expose
    val createdAt: String = "",
    @Expose
    @SerializedName("icon_url")
    val iconUrl: String = "",
    @Expose
    @SerializedName("id")
    val id: String = "",
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String = "",
    @Expose
    @SerializedName("url")
    val url: String = "",
    @Expose
    @SerializedName("value")
    val value: String = ""
)