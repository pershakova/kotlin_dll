package com.kotlin.geekbrains_dlls.mvp.model.cache

import com.kotlin.geekbrains_dlls.mvp.model.GithubRepository
import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {
    fun getRepositories(url: String): Single<List<GithubRepository>>

}