package com.rosalynbm.shoestoreinventory.data

import androidx.room.*
import com.rosalynbm.shoestoreinventory.models.Shoe
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface ShoeDao {

    @Transaction
    @Query("SELECT * FROM shoe_tb")
    fun getAll(): Maybe<List<Shoe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoe(shoe: Shoe): Completable

}