package ferrazfcf.repo_github_api.user_details_and_repos.data.repo_item

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoItemDTO(
    @Json(name = "name") val name: String?,
    @Json(name = "html_url") val pageUrl: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "created_at") val createdAt: String?,
    @Json(name = "updated_at") val updatedAt: String?
)
