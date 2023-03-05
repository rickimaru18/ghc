package com.bitflyer.ghc.domain.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserDetails(
    val id: Int,
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String?,
    val company: String?,
    val location: String?,
    val email: String?,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("public_gists")
    val publicGists: Int,
    val followers: Int,
    val following: Int
)
