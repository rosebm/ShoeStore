package com.rosalynbm.shoestoreinventory.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoe_tb")
data class Shoe(@PrimaryKey
                var id: Int,
                var name: String,
                var size: Int,
                var company: String,
                var description: String)
