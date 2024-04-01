package ferrazfcf.repo_github_api.user_details_and_repos.presentation

sealed interface UserDetailsAndReposEvent {
    @JvmInline
    value class SetUserLogin(val login: String): UserDetailsAndReposEvent
    data object LoadUserInfo: UserDetailsAndReposEvent
    data object LoadRepoList: UserDetailsAndReposEvent
    data object NavigateBack: UserDetailsAndReposEvent
}
