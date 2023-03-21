package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
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

        shoeViewModel.shoesList.observe(viewLifecycleOwner) { listShoes ->
            initShoeList(listShoes)
        }

        return binding.root
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