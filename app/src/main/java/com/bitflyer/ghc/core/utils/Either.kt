package com.bitflyer.ghc.core.utils

import com.bitflyer.ghc.core.errors.Failure

open class Either<T>(
    private val l: Failure? = null,
    private val r: T? = null
) {
    fun isLeft(): Boolean = l != null

    fun isRight(): Boolean = r != null

    fun fold(left: (l: Failure) -> Unit, right: (r: T) -> Unit) {
        if (l != null) {
            left(l!!)
        } else {
            right(r!!)
        }
    }
}

class Left<T>(l: Failure = Failure()) : Either<T>(l = l)

class Right<T>(r: T) : Either<T>(r = r)