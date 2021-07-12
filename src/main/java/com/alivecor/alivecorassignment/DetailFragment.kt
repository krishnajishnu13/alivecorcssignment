package com.alivecor.alivecorassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alivecor.alivecorassignment.databinding.FragmentDetailBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding.textViewDetailsFirstName.text =  resources.getString(R.string.details_first_name,sharedViewModel.firstName.value)//sharedViewModel.firstName.value
        binding.textViewDetailsLastName.text =  resources.getString(R.string.details_last_name,sharedViewModel.secondNam.value)//sharedViewModel.secondNam.value
        sharedViewModel.dateOfBirth.observe(viewLifecycleOwner, Observer {
            binding.textViewDetailsAge.text = resources.getString(R.string.details_age,it)
        })

        return binding.root
    }
}