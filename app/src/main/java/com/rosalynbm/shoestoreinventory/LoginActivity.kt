package com.rosalynbm.shoestoreinventory

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.rosalynbm.shoestoreinventory.ui.*
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var sharedPref: SharedPreferences
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((application as ShoeStoreApp).userRepository)
    }

    companion object {
        private const val SHOE_STORE = "shoe_store"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Timber.plant(Timber.DebugTree())

        login_btn.setOnClickListener(this)
        register_btn.setOnClickListener(this)

        loginViewModel.onUserSaved().observe(this, Observer { userSaved ->
            if (userSaved)
                callHostActivity()
        })

        loginViewModel.onUserValidated().observe(this, Observer { userValidated ->
            if (userValidated)
                callHostActivity()
            else
                Toast.makeText(this, "Wrong user or password", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onClick(view: View?) {
        when (view) {
            login_btn -> loginUser()
            register_btn -> registerUser()
        }
    }

    /**
     * Creates the new user and saves it on local db
     *
     */
    private fun registerUser() {
        val user: String = email_layout.editText?.text.toString()
        val pass: String = password_layout.editText?.text.toString()

        if (user.isNotEmpty() && pass.isNotEmpty())
            loginViewModel.saveUser(user, pass)
    }

    private fun callHostActivity() {
        val intent = Intent(this, HostActivity::class.java)
        startActivity(intent)
    }

    /**
     * Check if user already exists on db and compares for validation
     *
     */
    private fun loginUser() {
        val user: String = email_layout.editText?.text.toString()
        val pass: String = password_layout.editText?.text.toString()

        if (user.isNotEmpty() && pass.isNotEmpty())
            loginViewModel.getUser(user, pass)
    }

}