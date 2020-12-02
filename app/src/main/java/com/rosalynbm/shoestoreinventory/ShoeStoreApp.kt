package com.rosalynbm.shoestoreinventory

import android.app.Application
import com.rosalynbm.shoestoreinventory.business.ShoeUseCase
import com.rosalynbm.shoestoreinventory.business.UserUseCase
import com.rosalynbm.shoestoreinventory.data.AppDatabase

class ShoeStoreApp: Application() {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.getDatabase(this) }
    val shoeRepository by lazy { ShoeUseCase(database.getShoeDao()) }
    val userRepository by lazy { UserUseCase(database.getUserDao()) }

}