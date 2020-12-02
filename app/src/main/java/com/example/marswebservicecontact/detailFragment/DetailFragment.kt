package com.example.marswebservicecontact.detailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.marswebservicecontact.R
import com.example.marswebservicecontact.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private lateinit var binding:FragmentDetailBinding
    private lateinit var viewModel: DetailFragmentViewModel
    private lateinit var viewModelFactory: DetailFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.lifecycleOwner = this
        val marsProperty = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        viewModelFactory = DetailFragmentViewModelFactory(marsProperty, requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailFragmentViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }


}