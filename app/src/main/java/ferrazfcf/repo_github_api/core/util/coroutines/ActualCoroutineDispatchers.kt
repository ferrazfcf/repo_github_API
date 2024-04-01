package ferrazfcf.repo_github_api.core.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Inject

class ActualCoroutineDispatchers @Inject constructor() : CoroutineDispatchers {
    override val main: MainCoroutineDispatcher
        get() = Dispatchers.Main
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
}
