package com.alivecor.alivecorassignment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alivecor.alivecorassignment.databinding.FragmentMainBinding


class MainFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var binding:FragmentMainBinding
    private  var day: Int = 0
    private  var month: Int = 0
    private  var year: Int = 0
    private lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        sharedViewModel.firstName.observe(viewLifecycleOwner, Observer { it ->
            binding.editTextFirstName.setText(it)
        })
        sharedViewModel.secondNam.observe(viewLifecycleOwner, Observer { it ->
            binding.editTextLastName.setText(it)
        })

        arrayAdapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1,
                sharedViewModel.listDays)
        }!!

        binding.spinnerdays.adapter = arrayAdapter
        binding.spinnerdays.onItemSelectedListener = this

        arrayAdapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1,
                sharedViewModel.listMonths)
        }!!

        binding.spinnerMonths.adapter = arrayAdapter
        binding.spinnerMonths.onItemSelectedListener = this

        arrayAdapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                sharedViewModel.listYears)
        }!!

        binding.spinnerYear.adapter = arrayAdapter
        binding.spinnerYear.onItemSelectedListener = this

        binding.button.setOnClickListener {
            sharedViewModel.saveBioDetails(
                binding.editTextFirstName.text.toString(),
                binding.editTextLastName.text.toString()
            )
            sharedViewModel.getAgeTest(year, month, day)

            if(!binding.editTextFirstName.text.isEmpty() &&
                    !binding.editTextLastName.text.isEmpty()) {
                findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
            }else{
                Toast.makeText(context,"Please enter your details", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.spinner_months ->{
                month = position + 1
                Log.i("MainFragment", "Month Selected " + month )
            }
            R.id.spinner_year ->{
                year = sharedViewModel.listYears.get(position).toInt()
                Log.i("MainFragment", "Year Selected " + year)
            }
            R.id.spinnerdays ->{
                day = position + 1
                Log.i("MainFragment", "day Selected " + day)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainFragment", "onDestroy Called")

    }
}