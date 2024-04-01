package ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info

import ferrazfcf.repo_github_api.core.util.data.Resource

interface GetUserInfo {
    suspend operator fun invoke(login: String): Resource<UserInfo>
}
