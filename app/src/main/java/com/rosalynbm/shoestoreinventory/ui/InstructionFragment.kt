package com.rosalynbm.shoestoreinventory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.rosalynbm.shoestoreinventory.R
import kotlinx.android.synthetic.main.fragment_instruction.*

class InstructionFragment: Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_instruction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        instruction_btn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            instruction_btn -> Navigation.findNavController(instruction_btn).navigate(R.id.ShoeListFragment)
        }
    }

}