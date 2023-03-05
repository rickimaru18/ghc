package com.bitflyer.ghc.domain.models

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
)
