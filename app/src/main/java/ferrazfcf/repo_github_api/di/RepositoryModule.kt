package ferrazfcf.repo_github_api.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ferrazfcf.repo_github_api.user_details_and_repos.data.repository.RepoItemRepositoryImpl
import ferrazfcf.repo_github_api.user_details_and_repos.data.repository.UserInfoRepositoryImpl
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repository.RepoItemRepository
import ferrazfcf.repo_github_api.user_details_and_repos.domain.repository.UserInfoRepository
import ferrazfcf.repo_github_api.users_list_and_search.data.repository.UserItemRepositoryImpl
import ferrazfcf.repo_github_api.users_list_and_search.domain.repository.UserItemRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUserItemRepository(repository: UserItemRepositoryImpl): UserItemRepository

    @Binds
    @Singleton
    fun bindUserInfoRepository(repository: UserInfoRepositoryImpl): UserInfoRepository

    @Binds
    @Singleton
    fun bindRepoItemRepository(repository: RepoItemRepositoryImpl): RepoItemRepository
}
