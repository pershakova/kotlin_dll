package com.kotlin.geekbrains_dlls.mvp.model.repo.retrofit

import com.kotlin.geekbrains_dlls.mvp.model.GithubUser
import com.kotlin.geekbrains_dlls.mvp.model.IDataSource
import com.kotlin.geekbrains_dlls.mvp.model.IGithubUsersRepo
import com.kotlin.geekbrains_dlls.mvp.model.cache.IGithubUsersCache
import com.kotlin.geekbrains_dlls.mvp.model.cache.room.RoomGithubUsersCache
import com.kotlin.geekbrains_dlls.mvp.model.entity.room.Database
import com.kotlin.geekbrains_dlls.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database,
    var cache: IGithubUsersCache
) : IGithubUsersRepo {
    override val users: Single<List<GithubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                       // val roomUsers = users.map { user -> RoomGithubUser(user.id ?: "", user.login ?: "", user.avatarUrl ?: "", user.repos_url ?: "") }
                        cache.putUsers(users)
                       // db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            cache.users
           // Single.fromCallable {
            //    cache.users
            //    db.userDao.getAll().map { roomUser ->
              //      GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
               // }
          //  }
        }
    }.subscribeOn(Schedulers.io())


}

