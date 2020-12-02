package com.rosalynbm.shoestoreinventory.business

import com.rosalynbm.shoestoreinventory.data.ShoeDao
import com.rosalynbm.shoestoreinventory.models.Shoe
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

class ShoeUseCase(private val shoeDao: ShoeDao) {

    fun getShoeList(): Maybe<List<Shoe>> {
        return shoeDao.getAll()
            .subscribeOn(Schedulers.io())
    }

    fun insertShoe(shoe: Shoe): Completable {
        return shoeDao.insertShoe(shoe)
            .subscribeOn(Schedulers.io())
    }
}