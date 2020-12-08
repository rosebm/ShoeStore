package com.rosalynbm.shoestoreinventory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.rosalynbm.shoestoreinventory.R
import com.rosalynbm.shoestoreinventory.databinding.FragmentInstructionBinding
import kotlinx.android.synthetic.main.fragment_instruction.*

class InstructionFragment: Fragment(), View.OnClickListener {

    private var fragmentInstructionBinding: FragmentInstructionBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_instruction, container, false)
        fragmentInstructionBinding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInstructionBinding?.instructionBtn?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            instruction_btn -> Navigation.findNavController(instruction_btn).navigate(R.id.ShoeListFragment)
        }
    }

    override fun onDestroyView() {
        fragmentInstructionBinding = null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

}