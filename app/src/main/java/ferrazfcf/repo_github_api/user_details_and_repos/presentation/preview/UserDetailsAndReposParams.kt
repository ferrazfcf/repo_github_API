package ferrazfcf.repo_github_api.user_details_and_repos.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_item.RepoItem
import ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info.UserInfo
import ferrazfcf.repo_github_api.user_details_and_repos.presentation.UserDetailsAndReposState
import kotlinx.datetime.Clock

class UserDetailsAndReposParams : PreviewParameterProvider<UserDetailsAndReposState> {
    override val values: Sequence<UserDetailsAndReposState>
        get() = sequenceOf(
            loadedState,
            loadingUserState,
            loadingReposState,
            userErrorState,
            reposErrorState
        )

    companion object {
        private val now = Clock.System.now()
        private val userInfo = UserInfo(
            login = "ferrazfcf",
            avatar = "https://github.com/images/error/octocat_happy.gif",
            pageUrl = "https://github.com/ferrazfcf",
            name = "Felipe Ferraz",
            company = "Ferraz Company",
            blog = "https://github.com/ferrazfcf",
            location = "Brasil",
            email = "ferraz@email.com",
            bio = "Some nice bio"
        )
        private val repoItem = RepoItem(
            name = "nice-repo",
            pageUrl = "https://github.com/ferrazfcf",
            description = "nice repo",
            createdAt = now,
            updatedAt = now
        )
        val loadedState = UserDetailsAndReposState(
            userLogin = "ferrazfcf",
            userInfo = userInfo,
            repoList = listOf(
                repoItem,
                repoItem,
                repoItem,
                repoItem
            )
        )
        val loadingUserState = UserDetailsAndReposState(
            userLogin = "ferrazfcf",
            isUserInfoLoading = true,
            repoList = listOf(
                repoItem,
                repoItem,
                repoItem,
                repoItem
            )
        )
        val loadingReposState = UserDetailsAndReposState(
            userLogin = "ferrazfcf",
            userInfo = userInfo,
            isRepoListLoading = true
        )
        val userErrorState = UserDetailsAndReposState(
            userLogin = "ferrazfcf",
            userInfoError = R.string.defaul_error_message,
            repoList = listOf(
                repoItem,
                repoItem,
                repoItem,
                repoItem
            )
        )
        val reposErrorState = UserDetailsAndReposState(
            userLogin = "ferrazfcf",
            userInfo = userInfo,
            repoListError = R.string.defaul_error_message
        )
    }
}
