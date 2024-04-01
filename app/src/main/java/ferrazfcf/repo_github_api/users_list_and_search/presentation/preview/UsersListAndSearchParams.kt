package ferrazfcf.repo_github_api.users_list_and_search.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.users_list_and_search.domain.user_item.UserItem
import ferrazfcf.repo_github_api.users_list_and_search.presentation.UsersListAndSearchState

class UsersListAndSearchParams : PreviewParameterProvider<UsersListAndSearchState> {
    override val values: Sequence<UsersListAndSearchState>
        get() = sequenceOf(
            loadedState,
            searchState,
            loadingState,
            errorState
        )

    companion object {
        private val userItem = UserItem(
            login = "ferrazfcf",
            avatar = "https://github.com/images/error/octocat_happy.gif"
        )
        val loadedState = UsersListAndSearchState(
            users = listOf(
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem
            )
        )
        val searchState = UsersListAndSearchState(
            searchName = "ferrazfcf",
            performedSearch = true,
            users = listOf(
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem,
                userItem
            )
        )
        val loadingState = UsersListAndSearchState(
            searchName = "ferrazfcf",
            performedSearch = true,
            isLoading = true
        )
        val errorState = UsersListAndSearchState(
            searchName = "ferrazfcf",
            performedSearch = true,
            error = R.string.defaul_error_message
        )
    }
}
