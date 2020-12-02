package com.rosalynbm.shoestoreinventory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rosalynbm.shoestoreinventory.R
import com.rosalynbm.shoestoreinventory.ShoeStoreApp
import com.rosalynbm.shoestoreinventory.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoe_list.*
import timber.log.Timber

class ShoeListFragment: Fragment(), ListItemClickListener<Shoe>{

    private val shoeListViewModel: ShoeListViewModel = ShoeListViewModel(ShoeStoreApp().shoeRepository)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shoe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initShoeListAdapter()
        shoeListViewModel.getShoeList()

        shoeListViewModel.onShoeList().observe(viewLifecycleOwner, Observer {
            shoeListViewModel.getShoesListAdapter().submitList(it)
        })

        floating_button.setOnClickListener {
            Navigation.findNavController(floating_button).navigate(R.id.ShoeDetailFragment)
        }
    }

    private fun initShoeListAdapter() {
        shoes_rv.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        shoes_rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        shoes_rv.adapter = shoeListViewModel.getShoesListAdapter()
    }

    override fun onListItemClicked(item: Shoe) {
        Timber.d("Item selectected")
    }
}
