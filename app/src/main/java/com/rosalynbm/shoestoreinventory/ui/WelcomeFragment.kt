package com.rosalynbm.shoestoreinventory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rosalynbm.shoestoreinventory.R
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment: Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        welcome_btn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            welcome_btn -> findNavController().navigate(R.id.InstructionFragment)
        }
    }
}