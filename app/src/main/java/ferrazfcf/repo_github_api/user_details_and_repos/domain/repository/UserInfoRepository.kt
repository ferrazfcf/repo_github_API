package ferrazfcf.repo_github_api.user_details_and_repos.domain.repository

import ferrazfcf.repo_github_api.user_details_and_repos.data.user_info.UserInfoDTO

interface UserInfoRepository {
    suspend fun getUserInfo(login: String): UserInfoDTO
}
