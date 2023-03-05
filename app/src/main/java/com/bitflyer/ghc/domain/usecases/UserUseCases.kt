package com.bitflyer.ghc.domain.usecases

import com.bitflyer.ghc.core.utils.Either
import com.bitflyer.ghc.data.repositories.UserRepository
import com.bitflyer.ghc.domain.models.Event
import com.bitflyer.ghc.domain.models.User
import com.bitflyer.ghc.domain.models.UserDetails

class UserUseCases(
    private val repository: UserRepository = UserRepository()
) {
    /**
     * Get all GitHub users starting from ID [fromId].
     */
    suspend fun getAllUsers(fromId: Int = 0, limit: Int = 30): Either<List<User>> {
        require(fromId >= 0) { "[fromId] must be greater or equal to 0." }
        require(limit >= 1) { "[limit] must be greater or equal to 1" }

        return repository.getAllUsers(fromId, limit)
    }

    /**
     * Get GitHub user info.
     */
    suspend fun getUserDetails(username: String): Either<UserDetails> {
        require(username.isNotBlank()) { "[username] must not be blank." }
        return repository.getUserDetails(username)
    }

    /**
     * Get GitHub user public events.
     */
    suspend fun getUserEvents(
        username: String,
        page: Int = 1,
        limit: Int = 30
    ): Either<List<Event>> {
        require(username.isNotBlank()) { "[username] must not be blank." }
        require(page >= 1) { "[page] must be greater or equal to 1" }
        require(limit >= 1) { "[limit] must be greater or equal to 1" }

        return repository.getUserEvents(username, page, limit)
    }
}