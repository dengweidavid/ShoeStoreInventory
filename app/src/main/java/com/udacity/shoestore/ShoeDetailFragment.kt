package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment  : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            addShoe()
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun addShoe() {
        val shoe = Shoe(
            binding.shoeEdit.text.toString(),
            binding.shoeSizeSpinner.selectedItem.toString().toDouble(),
            binding.companyNameEdit.text.toString(),
            binding.shoeDescriptionEdit.text.toString()
        )

        shoeViewModel.addShoe(shoe)
    }

}