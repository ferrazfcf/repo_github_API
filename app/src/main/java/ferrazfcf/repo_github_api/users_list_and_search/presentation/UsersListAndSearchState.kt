package ferrazfcf.repo_github_api.users_list_and_search.presentation

import ferrazfcf.repo_github_api.users_list_and_search.domain.user_item.UserItem

data class UsersListAndSearchState(
    val isLoading: Boolean = false,
    val shouldShowResetButton: Boolean = false,
    val error: String? = null,
    val searchName: String = "",
    val users: List<UserItem> = emptyList()
)
