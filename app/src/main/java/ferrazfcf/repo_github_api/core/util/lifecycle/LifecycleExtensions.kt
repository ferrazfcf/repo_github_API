package ferrazfcf.repo_github_api.core.util.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun Lifecycle.ExecuteOnLifecycleEvent(vararg lifecycleEvent: Lifecycle.Event, onEvent: () -> Unit) {
    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            if (lifecycleEvent.contains(event)) {
                onEvent()
            }
        }
        this@ExecuteOnLifecycleEvent.addObserver(observer)
        onDispose {
            this@ExecuteOnLifecycleEvent.removeObserver(observer)
        }
    }
}
