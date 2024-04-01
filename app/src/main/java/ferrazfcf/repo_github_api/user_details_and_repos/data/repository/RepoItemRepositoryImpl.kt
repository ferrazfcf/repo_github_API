package ferrazfcf.repo_github_api.user_details_and_repos.data.repository

import ferrazfcf.repo_github_api.core.data.remote.Service
import ferrazfcf.repo_github_api.core.util.remote.resultOrThrow
import ferrazfcf.repo_github_api.user_details_and_repos.data.repo_item.RepoItemDTO
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repository.RepoItemRepository
import javax.inject.Inject

class RepoItemRepositoryImpl @Inject constructor(
    private val service: Service
) : RepoItemRepository {

    override suspend fun getUserRepos(login: String): List<RepoItemDTO> {
        return resultOrThrow {
            service.getUserRepos(login)
        }
    }
}
