package ferrazfcf.repo_github_api.user_details_and_repos.presentation

import androidx.annotation.StringRes
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_item.RepoItem
import ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info.UserInfo

data class UserDetailsAndReposState(
    val userLogin: String? = null,
    val userInfo: UserInfo? = null,
    val isUserInfoLoading: Boolean = false,
    @StringRes val userInfoError: Int? = null,
    val repoList: List<RepoItem> = emptyList(),
    val isRepoListLoading: Boolean = false,
    @StringRes val repoListError: Int? = null
)
