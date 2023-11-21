package com.example.cupcake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cupcake.databinding.FragmentFlavorNumberBinding
import com.example.cupcake.model.OrderViewModel

class FlavorNumberFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()

    private var binding: FragmentFlavorNumberBinding? = null

    private val _currentValue = MutableLiveData<Int>(0)
    val currentValue: LiveData<Int> = _currentValue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFlavorNumberBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            flavorNumberFragment = this@FlavorNumberFragment
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //private fun setCurrentValueVanilla(currentValueVanilla: Int) {
    //    _currentValueVanilla.value = currentValueVanilla
    //}
}