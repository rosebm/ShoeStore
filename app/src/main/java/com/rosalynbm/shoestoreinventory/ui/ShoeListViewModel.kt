package com.rosalynbm.shoestoreinventory.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rosalynbm.shoestoreinventory.BR
import com.rosalynbm.shoestoreinventory.business.ShoeUseCase
import com.rosalynbm.shoestoreinventory.models.Shoe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.util.*

class ShoeListViewModel(private val repository: ShoeUseCase): BaseObservable(),
    ListItemClickListener<Shoe> {

    private val shoeListLiveData = MutableLiveData<List<Shoe>>()
    fun onShoeList(): LiveData<List<Shoe>> = shoeListLiveData
    private var random = Random()

    private var shoeNamex: String = ""
    private var shoeCompanyx: String = ""
    private var shoeSizex: Int = 0
    private var shoeDescriptionx: String = ""

    private val shoeListAdapter: ShoeListAdapter = ShoeListAdapter(this)

    fun insertShoeToList() {
        val shoe = Shoe(random.nextInt(), getShoeName(), getShoeSize(), getShoeCompany(), getShoeDescription())

        CompositeDisposable(
            repository.insertShoe(shoe)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Success on insertShoe")
                }, {
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
                }, {
                    Timber.d("Error on getShoeList")
                })
        )
    }

    fun getShoesListAdapter() = shoeListAdapter

    override fun onListItemClicked(item: Shoe) {
        Timber.d("Item selectected ${item.name}")
    }


    @Bindable
    fun getShoeName(): String {
        return this.shoeNamex
    }

    fun setShoeName(name: String) {

        // Avoids infinite loops.
        if (shoeNamex == name) {
            return
        }
        shoeNamex = name
        // Notify observers of a new value.
        notifyPropertyChanged(BR.shoeName)
    }

    @Bindable
    fun getShoeCompany(): String {
        return this.shoeCompanyx
    }

    fun setShoeCompany(name: String) {

        // Avoids infinite loops.
        if (shoeCompanyx == name) {
            return
        }
        shoeCompanyx = name
        // Notify observers of a new value.
        notifyPropertyChanged(BR.shoeCompany)
    }

    @Bindable
    fun getShoeSize(): Int {
        return this.shoeSizex
    }

    fun setShoeSize(size: Int) {

        // Avoids infinite loops.
        if (shoeSizex == size) {
            return
        }
        shoeSizex = size
        // Notify observers of a new value.
        notifyPropertyChanged(BR.shoeSize)
    }

    @Bindable
    fun getShoeDescription(): String {
        return this.shoeDescriptionx
    }

    fun setShoeDescription(description: String) {

        // Avoids infinite loops.
        if (shoeDescriptionx == description) {
            return
        }
        shoeDescriptionx = description
        // Notify observers of a new value.
        notifyPropertyChanged(BR.shoeDescription)
    }

}

interface ListItemClickListener<T> {
    fun onListItemClicked(item: T)
}

