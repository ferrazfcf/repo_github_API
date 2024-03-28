package ferrazfcf.repo_github_api.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ferrazfcf.repo_github_api.core.util.ActualCoroutineDispatchers
import ferrazfcf.repo_github_api.core.util.CoroutineDispatchers

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @Binds
    fun bindCoroutineDispatchers(
        coroutineDispatchers: ActualCoroutineDispatchers
    ): CoroutineDispatchers
}
