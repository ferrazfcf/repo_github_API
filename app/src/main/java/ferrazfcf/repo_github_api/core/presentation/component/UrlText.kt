package ferrazfcf.repo_github_api.core.presentation.component

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat.startActivity
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.core.theme.RepoGithubTheme
import ferrazfcf.repo_github_api.core.util.asUriIntent

@Composable
fun UrlText(
    modifier: Modifier = Modifier,
    url: String,
    style: TextStyle,
    color: Color
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (text, copy, redirect) = createRefs()
        Text(
            modifier = Modifier.constrainAs(text) {
                linkTo(parent.top, parent.bottom)
                linkTo(parent.start, copy.start, 0.dp, 8.dp)
                width = Dimension.fillToConstraints
            },
            text = url,
            style = style,
            color = color,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(
            modifier = Modifier.constrainAs(copy) {
                linkTo(parent.top, parent.bottom)
                linkTo(text.end, redirect.start, 0.dp, 8.dp)
                width = Dimension.wrapContent
            },
            onClick = {
                clipboardManager.setText(
                    buildAnnotatedString { append(url) }
                )
                Toast.makeText(
                    context,
                    R.string.copied_to_clipboard,
                    Toast.LENGTH_LONG
                ).show()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ContentCopy,
                contentDescription = stringResource(id = R.string.copy_url),
                tint = color
            )
        }
        IconButton(
            modifier = Modifier.constrainAs(redirect) {
                linkTo(parent.top, parent.bottom)
                linkTo(copy.end, parent.end)
                width = Dimension.wrapContent
            },
            onClick = {
                val intent = url.asUriIntent()
                startActivity(
                    context,
                    intent,
                    null
                )
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                contentDescription = stringResource(id = R.string.open_url),
                tint = color
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun UrlTextPreview() {
    RepoGithubTheme {
        UrlText(
            url = "https://google.com",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
