package com.kotlin.geekbrains_dlls.mvp.model

import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>

    fun getRepos(url: String): Single<List<GithubRepository>>
}