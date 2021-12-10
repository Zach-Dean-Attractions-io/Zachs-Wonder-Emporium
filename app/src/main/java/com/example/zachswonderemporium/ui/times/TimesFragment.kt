package com.example.zachswonderemporium.ui.times

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.zachswonderemporium.R
import com.example.zachswonderemporium.data.Repository
import com.example.zachswonderemporium.data.local.ZachsWonderEmporiumDatabase
import com.example.zachswonderemporium.data.model.PointOfInterestLiveTime
import com.example.zachswonderemporium.databinding.FragmentTimesBinding
import com.example.zachswonderemporium.ui.ViewModelFactory
import com.example.zachswonderemporium.ui.planner.myplan.MyPlanAdapter
import org.koin.android.ext.android.inject

class TimesFragment: Fragment(), OnItemClickListener {

    private lateinit var timesViewModel: TimesViewModel
    private var _binding: FragmentTimesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)

        // Repository DI
        val repository: Repository by inject()

        timesViewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(TimesViewModel::class.java)

        _binding = FragmentTimesBinding.inflate(inflater, container, false)

        // Recycler View
        val timesAdapter = TimesAdapter(this)
        binding.liveTimesList.adapter = timesAdapter

        timesViewModel.pointsOfInterestWithLiveTimes.observe(viewLifecycleOwner) { poisWithLiveTimes ->
            timesAdapter.submitList(poisWithLiveTimes)
        }

        // Navigation to Details
        timesViewModel.navigateToDetails.observe(viewLifecycleOwner) { poiWithLiveTime ->
            poiWithLiveTime?.let {
                findNavController().navigate(TimesFragmentDirections.actionShowDetail(poiWithLiveTime))
                timesViewModel.navigationToDetailsComplete()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.refresh_times_button -> timesViewModel.refreshTimes()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.times_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onItemClicked(pointOfInterestLiveTime: PointOfInterestLiveTime) {
       timesViewModel.navigateToDetails(pointOfInterestLiveTime)
    }

}