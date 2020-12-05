package com.rosalynbm.shoestoreinventory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rosalynbm.shoestoreinventory.R
import com.rosalynbm.shoestoreinventory.databinding.FragmentWelcomeBinding
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment: Fragment(), View.OnClickListener {

    private var fragmentWelcomeBinding: FragmentWelcomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentWelcomeBinding.bind(view)
        fragmentWelcomeBinding = binding

        binding.welcomeBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            welcome_btn -> findNavController().navigate(R.id.InstructionFragment)
        }
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        fragmentWelcomeBinding = null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }
}