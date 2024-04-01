package ferrazfcf.repo_github_api.user_details_and_repos.domain.user_info

data class UserInfo(
    val login: String,
    val avatar: String?,
    val pageUrl: String,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?
)
