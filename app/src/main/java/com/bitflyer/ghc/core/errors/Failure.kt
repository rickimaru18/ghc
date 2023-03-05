package com.bitflyer.ghc.core.errors

data class Failure(
    val error: String = "Something went wrong",
    val code: Int? = null
)

