package com.rosalynbm.shoestoreinventory.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.rosalynbm.shoestoreinventory.R
import com.rosalynbm.shoestoreinventory.business.ShoeUseCase
import com.rosalynbm.shoestoreinventory.data.AppDatabase
import com.rosalynbm.shoestoreinventory.databinding.FragmentShoeDetailBinding
import kotlinx.android.synthetic.main.fragment_shoe_detail.*

class ShoeDetailFragment: Fragment(), View.OnClickListener {

    private var fragmentShoeDetailBinding: FragmentShoeDetailBinding? = null
    private lateinit var shoeRepository: ShoeUseCase
    private lateinit var shoeListViewModel: ShoeListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        shoeRepository = ShoeUseCase(AppDatabase.getDatabase(context).getShoeDao())
        shoeListViewModel = ShoeListViewModel(shoeRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shoe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentShoeDetailBinding.bind(view)
        binding.viewmodel = shoeListViewModel
        fragmentShoeDetailBinding = binding

        binding.saveBtn.setOnClickListener(this)
        binding.cancelBtn.setOnClickListener(this)

        shoeListViewModel.getShoeName()
    }

    override fun onClick(view: View?) {
        when (view) {
            save_btn -> saveShoe()
            cancel_btn -> Navigation.findNavController(cancel_btn).navigateUp()
        }
    }

    private fun saveShoe() {
        val name = shoe_name_et.text.toString()
        val company = shoe_company_et.text.toString()
        var size = 0.0
        if (!shoe_size_et.text.toString().isNullOrEmpty())
            size = shoe_size_et.text.toString().toDouble()
        val description = shoe_description_et.text.toString()

        if (name.isBlank().not() &&
            company.isBlank().not() &&
            size > 0 && description.isBlank().not())
            shoeListViewModel.insertShoeToList()

        Navigation.findNavController(save_btn).navigateUp()
    }

    override fun onDestroyView() {
        fragmentShoeDetailBinding = null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

}