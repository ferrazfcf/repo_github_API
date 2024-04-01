package ferrazfcf.repo_github_api.core.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ferrazfcf.repo_github_api.R
import ferrazfcf.repo_github_api.core.theme.RepoGithubTheme

@Composable
fun BasicTopBar(
    modifier: Modifier = Modifier,
    text: String,
    onBackButtonClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackButtonClick
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.navigate_back),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun BasicTopBarPreview() {
    RepoGithubTheme {
        BasicTopBar(
            text = "ferrazfcf",
            onBackButtonClick = {}
        )
    }
}
