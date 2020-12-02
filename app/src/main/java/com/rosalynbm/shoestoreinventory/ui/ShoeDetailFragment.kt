package com.rosalynbm.shoestoreinventory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.rosalynbm.shoestoreinventory.R
import com.rosalynbm.shoestoreinventory.ShoeStoreApp
import kotlinx.android.synthetic.main.fragment_shoe_detail.*

class ShoeDetailFragment: Fragment(), View.OnClickListener {

    private val shoeListViewModel: ShoeListViewModel = ShoeListViewModel(ShoeStoreApp().shoeRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shoe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
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
        if ( shoe_size_et.text != null)
            size = shoe_size_et.text.toString().toDouble()
        val description = shoe_description_et.text.toString()

        if (name.isBlank().not() && company.isBlank().not() && size > 0 && description.isBlank().not())
            shoeListViewModel.insertShoeToList(name, size, company, description)

        Navigation.findNavController(save_btn).navigateUp()
    }

}