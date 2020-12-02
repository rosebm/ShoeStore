package com.rosalynbm.shoestoreinventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rosalynbm.shoestoreinventory.models.Shoe
import com.rosalynbm.shoestoreinventory.models.User

@Database(entities = [User::class, Shoe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getShoeDao(): ShoeDao
    abstract fun getUserDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "room_database"

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}
