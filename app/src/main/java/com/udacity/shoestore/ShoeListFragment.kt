package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.LayoutShoeElementBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment  : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        setHasOptionsMenu(true)

        shoeViewModel.shoesList.observe(viewLifecycleOwner) { listShoes ->
            initShoeList(listShoes)
        }

        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun initShoeList(listShoes: MutableList<Shoe>) {
        val shoeScrollView = binding.shoeElementList

        for (shoe in listShoes) {
            val shoeBinding: LayoutShoeElementBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.layout_shoe_element,
                shoeScrollView,
                false
            )

            shoeBinding.shoeNameText.text = shoe.name
            shoeBinding.shoeCompanyText.text = shoe.company
            shoeBinding.shoeSizeText.text = shoe.size.toString()

            shoeScrollView.addView(shoeBinding.root)
        }

    }

}