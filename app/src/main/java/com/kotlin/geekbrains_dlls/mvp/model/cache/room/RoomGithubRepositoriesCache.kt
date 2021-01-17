package com.kotlin.geekbrains_dlls.mvp.model.cache.room

import com.kotlin.geekbrains_dlls.mvp.model.GithubRepository
import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import com.kotlin.geekbrains_dlls.mvp.model.cache.IGithubRepositoriesCache
import com.kotlin.geekbrains_dlls.mvp.model.entity.room.Database
import com.kotlin.geekbrains_dlls.mvp.model.entity.room.RoomGithubRepository
import com.kotlin.geekbrains_dlls.mvp.model.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class RoomGithubRepositoriesCache(database: Database): IGithubRepositoriesCache {

    private val db: Database


    init {
        db = database
    }

    override fun getRepositories(url: String): Single<List<GithubRepository>> {
        return Single.fromCallable {
            val roomUser: RoomGithubUser = db.userDao.findByUrl(url)
                ?: throw RuntimeException("No such user in cache")
            val roomGithubRepository: List<RoomGithubRepository> =
                db.repositoryDao.findForUser(roomUser.id)
            val githubRepositoryList: MutableList<GithubRepository> =
                ArrayList()
            for (roomGithubrepository in roomGithubRepository) {
                val githubRepository = GithubRepository(
                    roomGithubrepository.id,
                    roomGithubrepository.name,
                    roomGithubrepository.forksCount.toString()
                )
                githubRepositoryList.add(githubRepository)
            }
            githubRepositoryList
        }
    }

}