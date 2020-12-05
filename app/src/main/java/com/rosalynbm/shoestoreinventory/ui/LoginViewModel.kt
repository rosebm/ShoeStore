package com.rosalynbm.shoestoreinventory.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rosalynbm.shoestoreinventory.business.UserUseCase
import com.rosalynbm.shoestoreinventory.models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.util.*

class LoginViewModel(private val repository: UserUseCase): ViewModel() {

    private val userSavedLiveData = MutableLiveData<Boolean>()
    private val userValidatedLiveData = MutableLiveData<Boolean>()

    fun onUserSaved(): LiveData<Boolean> = userSavedLiveData
    fun onUserValidated(): LiveData<Boolean> = userValidatedLiveData

    /**
     * Save user on local DB an emits a livedata once is successful
     *
     * @param user
     * @param pass
     */
    fun saveUser(user: String, pass: String) {
        CompositeDisposable(repository.insertUser(User(Random().nextInt(), user, pass))
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userSavedLiveData.postValue(true)
                Timber.d("Success on saveUser")
            }, {
                Timber.d("Error on saveUser ${it.message}")
            }))
    }

    /**
     * Get the user from local DB an emits a livedata once is successful
     *
     * @param user
     * @param pass
     */
    fun getUser(user: String, pass: String) {
        CompositeDisposable(repository.getUser(user)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userValidatedLiveData.postValue((pass == it.password))
            },{
                Timber.d("Error on userFound: ${it.message}")
            }))
    }
}
