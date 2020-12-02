package com.rosalynbm.shoestoreinventory.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Password shouldn't be stored but sent to a server encrypted
 * only for quick test purpose
 *
 * @property userEmail
 * @property password
 */
@Entity(tableName = "user_tb")
class User (@PrimaryKey
            var id: Int,
            var userEmail: String,
            var password: String)