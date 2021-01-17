package com.kotlin.geekbrains_dlls.mvp.model

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    val users: Single<List<GithubUser>>


}