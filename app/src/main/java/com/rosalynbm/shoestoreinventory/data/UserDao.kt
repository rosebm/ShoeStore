package com.rosalynbm.shoestoreinventory.data

import androidx.room.*
import com.rosalynbm.shoestoreinventory.models.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM user_tb where userEmail = :user_email")
    fun getUser(user_email: String): Single<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Completable
}