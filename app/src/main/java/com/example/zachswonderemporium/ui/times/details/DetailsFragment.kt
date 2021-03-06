package com.example.zachswonderemporium.ui.times.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.zachswonderemporium.databinding.FragmentDetailsBinding

class DetailsFragment: Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.poiLiveTime = DetailsFragmentArgs.fromBundle(requireArguments()).selectedPointOfInterest

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}