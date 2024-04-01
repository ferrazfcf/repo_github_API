package ferrazfcf.repo_github_api.users_list_and_search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.core.domain.GithubRequestException
import ferrazfcf.repo_github_api.core.util.coroutines.CoroutineDispatchers
import ferrazfcf.repo_github_api.core.util.data.Resource
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_item.UserItem
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_list.GetUserList
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_list.GetUserListByName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListAndSearchViewModel @Inject constructor(
    private val getUserList: GetUserList,
    private val getUserListByName: GetUserListByName,
    private val dispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow(UsersListAndSearchState())
    val state: StateFlow<UsersListAndSearchState> = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        UsersListAndSearchState()
    )

    init {
        onEvent(UsersListAndSearchEvent.LoadUsers)
    }

    fun onEvent(event: UsersListAndSearchEvent) {
        when (event) {
            is UsersListAndSearchEvent.ChangeSearchText -> _state.update { state ->
                state.copy(searchName = event.login)
            }
            UsersListAndSearchEvent.ClearSearchText -> _state.update { state ->
                state.copy(searchName = "")
            }
            UsersListAndSearchEvent.OnErrorSeen -> _state.update { state ->
                state.copy(error = null)
            }
            UsersListAndSearchEvent.LoadUsers -> loadOrResetUsers(state.value)
            UsersListAndSearchEvent.SearchUsersByName -> searchUsersByName(state.value)
            UsersListAndSearchEvent.ResetUsers -> loadOrResetUsers(state.value)
            else -> Unit
        }
    }

    private fun loadOrResetUsers(stateOnLoadStart: UsersListAndSearchState) {
        if (stateOnLoadStart.isLoading) return
        viewModelScope.launch(dispatchers.io) {
            _state.update { state ->
                state.copy(
                    searchName = "",
                    isLoading = true,
                    performedSearch = false
                )
            }

            val result = getUserList()
            _state.update { state ->
                updateUsersListAndSearchState(result, state)
            }
        }
    }

    private fun searchUsersByName(stateOnLoadStart: UsersListAndSearchState) {

        if (stateOnLoadStart.isLoading || stateOnLoadStart.searchName.isBlank()) return
        viewModelScope.launch(dispatchers.io) {
            _state.update { state ->
                state.copy(
                    isLoading = true,
                    performedSearch = true
                )
            }

            val result = getUserListByName(stateOnLoadStart.searchName)
            _state.update { state ->
                updateUsersListAndSearchState(result, state)
            }
        }
    }

    private fun updateUsersListAndSearchState(
        result: Resource<List<UserItem>>,
        state: UsersListAndSearchState
    ): UsersListAndSearchState {
        return when (result) {
            is Resource.Success -> state.copy(
                isLoading = false,
                users = result.data
            )

            is Resource.Error -> state.copy(
                isLoading = false,
                error = (result.throwable as? GithubRequestException)?.error?.message
                    ?: R.string.defaul_error_message
            )
        }
    }
}
