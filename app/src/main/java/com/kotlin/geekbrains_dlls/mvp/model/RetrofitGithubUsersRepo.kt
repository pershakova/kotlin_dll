package com.kotlin.geekbrains_dlls.mvp.model

import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource): IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())

    override fun getRepos(url:String) = api.getRepos(url).subscribeOn(Schedulers.io())
}