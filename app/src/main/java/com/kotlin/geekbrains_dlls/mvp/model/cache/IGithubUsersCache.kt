package com.kotlin.geekbrains_dlls.mvp.model.cache

import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    val users: Single<List<GithubUser>>

    fun putUsers(users: List<GithubUser>): Completable
}