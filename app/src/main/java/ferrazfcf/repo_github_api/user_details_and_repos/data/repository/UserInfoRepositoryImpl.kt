package ferrazfcf.repo_github_api.user_details_and_repos.data.repository

import ferrazfcf.repo_github_api.core.data.remote.Service
import ferrazfcf.repo_github_api.core.util.remote.resultOrThrow
import ferrazfcf.repo_github_api.user_details_and_repos.data.user_info.UserInfoDTO
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val service: Service
) : UserInfoRepository {

    override suspend fun getUserInfo(login: String): UserInfoDTO {
        return resultOrThrow {
            service.getUserByName(login)
        }
    }
}
