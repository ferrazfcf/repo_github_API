package ferrazfcf.repo_github_api.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.core.domain.GithubRequestError
import ferrazfcf.repo_github_api.core.theme.RepoGithubTheme

@Composable
fun ErrorRetry(
    modifier: Modifier = Modifier,
    error: String,
    color: Color,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = error,
            style = MaterialTheme.typography.headlineMedium.copy(
                lineBreak = LineBreak(
                    LineBreak.Strategy.Balanced,
                    LineBreak.Strictness.Default,
                    LineBreak.WordBreak.Default
                )
            ),
            color = color,
            textAlign = TextAlign.Center,
        )
        Button(onClick = onRetry) {
            Text(
                text = stringResource(id = R.string.retry),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ErrorRetryPreview() {
    RepoGithubTheme {
        ErrorRetry(
            error = stringResource(id = GithubRequestError.SERVER_ERROR.message),
            color = MaterialTheme.colorScheme.onSurface
        ) {}
    }
}
