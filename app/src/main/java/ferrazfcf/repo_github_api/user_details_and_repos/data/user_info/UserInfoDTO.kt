package ferrazfcf.repo_github_api.user_details_and_repos.data.user_info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfoDTO(
    @Json(name = "login") val login: String?,
    @Json(name = "avatar_url") val avatar: String?,
    @Json(name = "html_url") val pageUrl: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "company") val company: String?,
    @Json(name = "blog") val blog: String?,
    @Json(name = "location") val location: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "bio") val bio: String?
)
