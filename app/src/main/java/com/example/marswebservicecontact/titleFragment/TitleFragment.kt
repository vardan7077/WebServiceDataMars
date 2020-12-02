package com.example.marswebservicecontact.titleFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.marswebservicecontact.R
import com.example.marswebservicecontact.databinding.FragmentTitleBinding
import com.example.marswebservicecontact.network.MarsAPIFilter
import com.example.marswebservicecontact.photoGridAdapter.PhotoGridAdapter


class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding
    private lateinit var viewModel: TitleFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTitleBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(TitleFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.onClickListener{
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToDetailFragment(it))
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when(item.itemId){
                R.id.show_rent_menu -> MarsAPIFilter.SHOW_RENT
                R.id.show_buy_menu -> MarsAPIFilter.SHOW_BUY
                else -> MarsAPIFilter.SHOW_ALL
            }
        )
        return true
    }

}