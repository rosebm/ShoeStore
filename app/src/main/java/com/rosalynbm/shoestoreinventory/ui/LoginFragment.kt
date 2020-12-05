package com.rosalynbm.shoestoreinventory.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.rosalynbm.shoestoreinventory.R
import com.rosalynbm.shoestoreinventory.business.UserUseCase
import com.rosalynbm.shoestoreinventory.data.AppDatabase
import com.rosalynbm.shoestoreinventory.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber


class LoginFragment: Fragment(), View.OnClickListener {

    // Using lateinit var so the repository and loginViewModel are only created when they're needed
    // rather than when the application starts
    private lateinit var userRepository: UserUseCase
    private var fragmentLoginBinding: FragmentLoginBinding? = null
    private lateinit var loginViewModel: LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userRepository = UserUseCase(AppDatabase.getDatabase(context).getUserDao())
        loginViewModel = LoginViewModel(userRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLoginBinding.bind(view)
        fragmentLoginBinding = binding

        Timber.plant(Timber.DebugTree())

        binding.loginBtn.setOnClickListener(this)
        binding.registerBtn.setOnClickListener(this)

        loginViewModel.onUserSaved().observe(viewLifecycleOwner, Observer { userSaved ->
            if (userSaved)
                Navigation.findNavController(binding.registerBtn).navigate(R.id.WelcomeFragment)
        })

        loginViewModel.onUserValidated().observe(viewLifecycleOwner, Observer { userValidated ->
            if (userValidated)
                Navigation.findNavController(binding.loginBtn).navigate(R.id.WelcomeFragment)
            else
                Toast.makeText(requireContext(), "Wrong user or password", Toast.LENGTH_SHORT)
                    .show()
        })

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
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

    override fun onDestroyView() {
        fragmentLoginBinding = null
        super.onDestroyView()
    }

}