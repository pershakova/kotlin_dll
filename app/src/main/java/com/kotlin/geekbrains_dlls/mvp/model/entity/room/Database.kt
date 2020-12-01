package com.kotlin.geekbrains_dlls.mvp.model.entity.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlin.geekbrains_dlls.mvp.model.entity.room.DAO.RepositoryDao
import com.kotlin.geekbrains_dlls.mvp.model.entity.room.DAO.UserDao

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}
