package com.rosalynbm.shoestoreinventory.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rosalynbm.shoestoreinventory.R
import com.rosalynbm.shoestoreinventory.business.ShoeUseCase
import com.rosalynbm.shoestoreinventory.data.AppDatabase
import com.rosalynbm.shoestoreinventory.databinding.FragmentShoeListBinding
import com.rosalynbm.shoestoreinventory.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoe_list.*
import timber.log.Timber


class ShoeListFragment: Fragment(), ListItemClickListener<Shoe>{

    private var fragmentShoeListBinding: FragmentShoeListBinding? = null
    private lateinit var shoeRepository: ShoeUseCase
    private lateinit var shoeListViewModel: ShoeListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        shoeRepository = ShoeUseCase(AppDatabase.getDatabase(context).getShoeDao())
        shoeListViewModel = ShoeListViewModel(shoeRepository)
    }

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
        val view =  inflater.inflate(R.layout.fragment_shoe_list, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentShoeListBinding.bind(view)
        fragmentShoeListBinding = binding

        initShoeListAdapter()
        shoeListViewModel.getShoeList()

        shoeListViewModel.onShoeList().observe(viewLifecycleOwner, Observer {
            shoeListViewModel.getShoesListAdapter().submitList(it)
        })

        binding.floatingButton.setOnClickListener {
            Navigation.findNavController(floating_button).navigate(R.id.ShoeDetailFragment)
        }
    }

    private fun initShoeListAdapter() {
        shoes_rv.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        shoes_rv.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        shoes_rv.adapter = shoeListViewModel.getShoesListAdapter()
    }

    override fun onListItemClicked(item: Shoe) {
        Timber.d("Item selectected")
    }

    override fun onDestroyView() {
        fragmentShoeListBinding = null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menus, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
