package ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_list

import ferrazfcf.repo_github_api.core.util.data.Resource
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_item.RepoItem
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_item.toRepoItem
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repository.RepoItemRepository
import kotlinx.coroutines.ensureActive
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GetRepoListImpl @Inject constructor(
    private val repository: RepoItemRepository
) : GetRepoList {

    override suspend fun invoke(login: String): Resource<List<RepoItem>> {
        return repository.runCatching {
            val users = getUserRepos(login).mapNotNull { dto ->
                dto.toRepoItem()
            }
            coroutineContext.ensureActive()
            Resource.Success(users)
        }.getOrElse { error ->
            error.printStackTrace()
            Resource.Error(error)
        }
    }
}
