package com.bitflyer.ghc.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitflyer.ghc.core.utils.Either
import com.bitflyer.ghc.domain.models.User
import com.bitflyer.ghc.domain.usecases.UserUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCases: UserUseCases = UserUseCases()
) : ViewModel() {
    var users: List<User>? by mutableStateOf<List<User>?>(null)
        private set

    var isLoading: Boolean by mutableStateOf<Boolean>(true)
        private set

    var hasNextPage: Boolean = true
        private set

    private val _limit: Int = 30
    private var _fromId: Int = 0

    init {
        viewModelScope.launch {
            delay(1)
            getAllUsers()
        }
    }

    fun userCount() = users?.size ?: 0

    fun getAllUsers() = viewModelScope.launch(Dispatchers.IO) {
        isLoading = true

        val res: Either<List<User>> = useCases.getAllUsers(_fromId, _limit)

        res.fold(
            {
                hasNextPage = true

                if (users?.isEmpty() == true) {
                    // Set to null to display error composable.
                    users = null
                }
            },
            {
                val finalUsers: MutableList<User> = mutableListOf<User>()

                if (users?.isNotEmpty() == true) {
                    finalUsers.addAll(users!!)
                }

                finalUsers.addAll(it)
                hasNextPage = it.size == _limit
                users = finalUsers
            }
        )

        isLoading = false
    }

    fun nextPage() {
        if (isLoading || users == null) {
            return
        }

        _fromId = users!!.last().id
        getAllUsers()
    }
}

