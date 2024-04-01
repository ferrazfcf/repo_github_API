package ferrazfcf.repo_github_api.users_list_and_search.presentation

import androidx.annotation.StringRes
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_item.UserItem

data class UsersListAndSearchState(
    val isLoading: Boolean = false,
    val performedSearch: Boolean = false,
    @StringRes val error: Int? = null,
    val searchName: String = "",
    val users: List<UserItem> = emptyList()
)
