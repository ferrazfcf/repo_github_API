package ferrazfcf.repo_github_api.users_list_and_search.presentation

sealed interface UsersListAndSearchEvent {
    @JvmInline
    value class ChangeSearchText(val user: String): UsersListAndSearchEvent
    @JvmInline
    value class ShowUserDetailsAndRepos(val user: String): UsersListAndSearchEvent
    data object LoadUsers: UsersListAndSearchEvent
    data object SearchUsersByName: UsersListAndSearchEvent
    data object ResetUsers: UsersListAndSearchEvent
    data object ClearSearchText: UsersListAndSearchEvent
    data object OnErrorSeen: UsersListAndSearchEvent
}
