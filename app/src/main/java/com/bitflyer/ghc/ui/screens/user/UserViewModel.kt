package com.bitflyer.ghc.ui.screens.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitflyer.ghc.core.utils.Either
import com.bitflyer.ghc.domain.models.Event
import com.bitflyer.ghc.domain.models.UserDetails
import com.bitflyer.ghc.domain.usecases.UserUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel(
    private val username: String,
    private val useCases: UserUseCases = UserUseCases()
) : ViewModel() {
    var user: UserDetails? by mutableStateOf<UserDetails?>(null)
        private set

    var events: List<Event>? by mutableStateOf<List<Event>?>(null)
        private set

    var isLoading: Boolean by mutableStateOf<Boolean>(true)
        private set

    var isLoadingEvents: Boolean by mutableStateOf<Boolean>(true)
        private set

    var hasNextPage: Boolean = true
        private set

    private val _limit: Int = 30
    private var _page: Int = 1

    init {
        viewModelScope.launch {
            delay(1)
            getUser()
            getUserEvents()
        }
    }

    fun getUser() = viewModelScope.launch(Dispatchers.IO) {
        isLoading = true

        val res: Either<UserDetails> = useCases.getUserDetails(username)

        res.fold(
            {
                // Do nothing.
            },
            {
                user = it
            }
        )

        isLoading = false
    }

    private fun getUserEvents() = viewModelScope.launch(Dispatchers.IO) {
        isLoadingEvents = true

        val res: Either<List<Event>> = useCases.getUserEvents(username, _page, _limit)

        res.fold(
            {
                hasNextPage = true

                if (events?.isEmpty() == true) {
                    // Set to null to display error composable.
                    events = null
                }
            },
            {
                val finalEvents: MutableList<Event> = mutableListOf<Event>()

                if (events?.isNotEmpty() == true) {
                    finalEvents.addAll(events!!)
                }

                finalEvents.addAll(it)
                hasNextPage = it.size == _limit
                events = finalEvents
            }
        )

        isLoadingEvents = false
    }

    fun nextPage() {
        if (isLoadingEvents || events == null) {
            return
        }

        _page++
        getUserEvents()
    }
}