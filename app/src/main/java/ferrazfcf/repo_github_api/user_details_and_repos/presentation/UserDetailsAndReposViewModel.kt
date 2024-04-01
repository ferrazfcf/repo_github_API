package ferrazfcf.repo_github_api.user_details_and_repos.presentation

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.core.domain.GithubRequestException
import ferrazfcf.repo_github_api.core.util.coroutines.CoroutineDispatchers
import ferrazfcf.repo_github_api.core.util.data.Resource
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_item.RepoItem
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_list.GetRepoList
import ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info.GetUserInfo
import ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsAndReposViewModel @Inject constructor(
    private val getUserInfo: GetUserInfo,
    private val getRepoList: GetRepoList,
    private val dispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow(UserDetailsAndReposState())
    val state: StateFlow<UserDetailsAndReposState> = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        UserDetailsAndReposState()
    )

    fun onEvent(event: UserDetailsAndReposEvent) {
        when (event) {
            UserDetailsAndReposEvent.LoadRepoList -> loadRepoList(state.value)
            UserDetailsAndReposEvent.LoadUserInfo -> loadUserInfo(state.value)
            is UserDetailsAndReposEvent.SetUserLogin -> initViewModel(event)
            else -> Unit
        }
    }

    private fun initViewModel(event: UserDetailsAndReposEvent.SetUserLogin) {
        _state.update { state ->
            state.copy(userLogin = event.login)
        }
        onEvent(UserDetailsAndReposEvent.LoadUserInfo)
        onEvent(UserDetailsAndReposEvent.LoadRepoList)
    }

    private fun loadRepoList(stateOnLoadStart: UserDetailsAndReposState) {
        if (stateOnLoadStart.isRepoListLoading || stateOnLoadStart.userLogin.isNullOrBlank()) return
        viewModelScope.launch(dispatchers.io) {
            _state.update { state ->
                state.copy(
                    isRepoListLoading = true,
                    repoListError = null
                )
            }

            val result = getRepoList(stateOnLoadStart.userLogin)
            _state.update { state ->
                updateRepoListState(result, state)
            }
        }
    }

    private fun updateRepoListState(
        result: Resource<List<RepoItem>>,
        state: UserDetailsAndReposState
    ): UserDetailsAndReposState {
        return when (result) {
            is Resource.Success -> state.copy(
                isRepoListLoading = false,
                repoList = result.data
            )

            is Resource.Error -> {
                val errorMessage = (result.throwable as? GithubRequestException)?.error?.message
                    ?: R.string.defaul_error_message
                state.copy(
                    isRepoListLoading = false,
                    repoListError = errorMessage
                )
            }
        }
    }

    private fun loadUserInfo(stateOnLoadStart: UserDetailsAndReposState) {
        if (stateOnLoadStart.isUserInfoLoading || stateOnLoadStart.userLogin.isNullOrBlank()) return
        viewModelScope.launch(dispatchers.io) {
            _state.update { state ->
                state.copy(
                    isUserInfoLoading = true,
                    userInfoError = null
                )
            }

            val result = getUserInfo(stateOnLoadStart.userLogin)
            _state.update { state ->
                updateUserInfoState(result, state)
            }
        }
    }

    private fun updateUserInfoState(
        result: Resource<UserInfo>,
        state: UserDetailsAndReposState
    ): UserDetailsAndReposState {
        return when (result) {
            is Resource.Success -> state.copy(
                isUserInfoLoading = false,
                userInfo = result.data
            )

            is Resource.Error -> {
                val errorMessage = (result.throwable as? GithubRequestException)?.error?.message
                    ?: R.string.defaul_error_message
                state.copy(
                    isUserInfoLoading = false,
                    userInfoError = errorMessage
                )
            }
        }
    }
}
