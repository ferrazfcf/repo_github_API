package ferrazfcf.repo_github_api.user_details_and_repos.domain.repository

import ferrazfcf.repo_github_api.user_details_and_repos.data.repo_item.RepoItemDTO

interface RepoItemRepository {
    suspend fun getUserRepos(login: String): List<RepoItemDTO>
}
