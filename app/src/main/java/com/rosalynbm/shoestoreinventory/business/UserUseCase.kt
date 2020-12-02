package com.rosalynbm.shoestoreinventory.business

import com.rosalynbm.shoestoreinventory.data.UserDao
import com.rosalynbm.shoestoreinventory.models.User
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UserUseCase(private val userDao: UserDao) {

    fun insertUser(user: User): Completable {
        return userDao.insertUser(user)
            .subscribeOn(Schedulers.io())
    }

    fun getUser(user_email: String): Single<User> {
        return userDao.getUser(user_email)
            .subscribeOn(Schedulers.io())
    }
}