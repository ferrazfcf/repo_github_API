package ferrazfcf.repo_github_api.core.util

import android.content.Intent
import android.net.Uri

fun String.asUriIntent(): Intent {
    val uriString = this.appendPrefix()
    return Intent(
        Intent.ACTION_VIEW,
        Uri.parse(uriString)
    ).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}

private fun String.appendPrefix() = if (!this.startsWith("http")) {
    "https://$this"
} else {
    this
}
