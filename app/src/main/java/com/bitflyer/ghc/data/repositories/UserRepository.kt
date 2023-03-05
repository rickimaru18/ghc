package com.bitflyer.ghc.data.repositories

import com.bitflyer.ghc.core.utils.Either
import com.bitflyer.ghc.data.datasources.GitHubDataSource
import com.bitflyer.ghc.domain.models.Event
import com.bitflyer.ghc.domain.models.User
import com.bitflyer.ghc.domain.models.UserDetails

class UserRepository(
    private val dataSource: GitHubDataSource = GitHubDataSource()
) {
    /**
     * Get all GitHub users starting from ID [fromId].
     */
    suspend fun getAllUsers(fromId: Int = 0, limit: Int = 30): Either<List<User>> =
        dataSource.request<List<User>> {
            it.getAllUsers(fromId, limit)
        }

    /**
     * Get GitHub user info.
     */
    suspend fun getUserDetails(username: String): Either<UserDetails> =
        dataSource.request<UserDetails> {
            it.getUserDetails(username)
        }

    /**
     * Get GitHub user public events.
     */
    suspend fun getUserEvents(
        username: String,
        page: Int = 1,
        limit: Int = 30
    ): Either<List<Event>> =
        dataSource.request<List<Event>> {
            it.getUserEvents(username, page, limit)
        }
}