package com.kotlin.geekbrains_dlls.mvp.model.cache.room

import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import com.kotlin.geekbrains_dlls.mvp.model.cache.IGithubUsersCache
import com.kotlin.geekbrains_dlls.mvp.model.entity.room.Database
import com.kotlin.geekbrains_dlls.mvp.model.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class RoomGithubUsersCache(database: Database) : IGithubUsersCache {

    private val db: Database


    init {
        db = database
    }

    override val users: Single<List<GithubUser>>
        get() = Single.fromCallable {
            val roomGithubUsers: List<RoomGithubUser> = db.userDao.getAll()
            val users: MutableList<GithubUser> = ArrayList()
            for (roomGithubUser in roomGithubUsers) {
                val githubUser = GithubUser(
                    roomGithubUser.id,
                    roomGithubUser.login,
                    roomGithubUser.avatarUrl,
                    roomGithubUser.reposUrl
                )
                users.add(githubUser)
            }
            users
        }

    override fun putUsers(users: List<GithubUser>): Completable {
        return Completable.fromAction {
            val roomGithubUsers: MutableList<RoomGithubUser> =
                ArrayList<RoomGithubUser>()
            for (user in users) {
                val roomUser = RoomGithubUser(
                    user.id,
                    user.login,
                    user.avatarUrl,
                    user.repos_url
                )
                roomGithubUsers.add(roomUser)
            }
            db.userDao.insert(roomGithubUsers)
        }.subscribeOn(Schedulers.io())
    }
}