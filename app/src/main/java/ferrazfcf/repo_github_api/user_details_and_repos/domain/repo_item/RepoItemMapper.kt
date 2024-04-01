package ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_item

import android.util.Log
import ferrazfcf.repo_github_api.user_details_and_repos.data.repo_item.RepoItemDTO
import kotlinx.datetime.Instant

fun RepoItemDTO.toRepoItem(): RepoItem? {
    return runCatching {
        requireNotNull(name) { "Repo name should not be null" }
        requireNotNull(pageUrl) { "Repo page url should not be null" }
        requireNotNull(createdAt) { "Repo create date should not be null" }
        RepoItem(
            name = name,
            pageUrl = pageUrl,
            description = description,
            createdAt = Instant.parse(createdAt),
            updatedAt = updatedAt?.let(Instant::parse)
        )
    }.onFailure {
        Log.e("MAPPER_ERROR", "RepoItem", it)
    }.getOrNull()
}
