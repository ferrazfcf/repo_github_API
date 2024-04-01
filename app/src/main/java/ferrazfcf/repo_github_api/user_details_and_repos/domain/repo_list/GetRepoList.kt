package ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_list

import ferrazfcf.repo_github_api.core.util.data.Resource
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_item.RepoItem

interface GetRepoList {
    suspend operator fun invoke(login: String): Resource<List<RepoItem>>
}
