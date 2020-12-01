package com.kotlin.geekbrains_dlls.mvp.model.repo.retrofit

import com.kotlin.geekbrains_dlls.mvp.model.GithubRepository
import com.kotlin.geekbrains_dlls.mvp.model.IDataSource
import com.kotlin.geekbrains_dlls.mvp.model.cache.IGithubRepositoriesCache
import com.kotlin.geekbrains_dlls.mvp.model.entity.room.Database
import com.kotlin.geekbrains_dlls.mvp.model.network.INetworkStatus
import com.kotlin.geekbrains_dlls.mvp.model.repo.IGithubRepositoriesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    database: Database,
    var cache: IGithubRepositoriesCache
) :
    IGithubRepositoriesRepo {

    private val db: Database

    init {
        db = database

    }

    override fun getRepositories(url: String): Single<List<GithubRepository>> {
        return url.let { api.getRepos(it).subscribeOn(Schedulers.io()) }
    }

}