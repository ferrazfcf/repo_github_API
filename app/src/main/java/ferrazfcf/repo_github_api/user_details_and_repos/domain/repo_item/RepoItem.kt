package ferrazfcf.repo_github_api.user_details_and_repos.domain.repo_item

import ferrazfcf.repo_github_api.core.data.datetime.DatetimeFormatPatterns
import ferrazfcf.repo_github_api.core.util.datetime.format
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class RepoItem(
    val name: String,
    val pageUrl: String,
    val description: String?,
    private val createdAt: Instant,
    private val updatedAt: Instant?
) {

    val formattedCreatedAt: String by lazy {
        createdAt.toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .format(DatetimeFormatPatterns.FULL_DATE.pattern)
    }

    val formattedUpdatedAt: String? by lazy {
        updatedAt?.toLocalDateTime(TimeZone.currentSystemDefault())
            ?.date
            ?.format(DatetimeFormatPatterns.FULL_DATE.pattern)
    }
}
