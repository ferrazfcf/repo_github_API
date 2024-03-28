package ferrazfcf.repo_github_api.users_list_and_search.data.user_item

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserItemDTO(
    @Json(name = "login") val name: String?,
    @Json(name = "avatar_url") val avatar: String?
)
