package com.rosalynbm.shoestoreinventory.ui

import androidx.lifecycle.*
import com.rosalynbm.shoestoreinventory.business.ShoeUseCase
import com.rosalynbm.shoestoreinventory.models.Shoe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.util.*

class ShoeListViewModel(private val repository: ShoeUseCase): ViewModel(),
    ListItemClickListener<Shoe> {

    private val shoeListLiveData = MutableLiveData<List<Shoe>>()
    fun onShoeList(): LiveData<List<Shoe>> = shoeListLiveData
    private var random = Random()

    private val shoeListAdapter: ShoeListAdapter = ShoeListAdapter(this)

    fun insertShoeToList(name: String, size: Double, company: String, description: String) {
        val shoe = Shoe(random.nextInt(), name, size, company, description)

        CompositeDisposable(
            repository.insertShoe(shoe)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Success on insertShoe")
                },{
                    Timber.d("Error inserting new shoe")
                })
        )
    }

    /**
     * Get the shoe list from the DB and emits a livedata with the date
     *
     */
    fun getShoeList() {
        CompositeDisposable(
            repository.getShoeList()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    shoeListLiveData.postValue(it)
                    Timber.d("Success on getShoeList")
                },{
                    Timber.d("Error on getShoeList")
                })
        )
    }

    fun getShoesListAdapter() = shoeListAdapter

    override fun onListItemClicked(item: Shoe) {
        Timber.d("Item selectected ${item.name}")
    }
}

interface ListItemClickListener<T> {
    fun onListItemClicked(item: T)
}

