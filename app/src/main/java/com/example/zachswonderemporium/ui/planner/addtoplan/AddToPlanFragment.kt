package com.example.zachswonderemporium.ui.planner.addtoplan

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.zachswonderemporium.R
import com.example.zachswonderemporium.data.Repository
import com.example.zachswonderemporium.data.local.ZachsWonderEmporiumDatabase
import com.example.zachswonderemporium.data.model.PointOfInterest
import com.example.zachswonderemporium.databinding.FragmentAddToPlanBinding
import com.example.zachswonderemporium.databinding.FragmentMyPlanBinding
import com.example.zachswonderemporium.databinding.FragmentTimesBinding
import com.example.zachswonderemporium.ui.ViewModelFactory
import com.example.zachswonderemporium.ui.planner.myplan.MyPlanAdapter
import com.example.zachswonderemporium.ui.times.TimesViewModel
import org.koin.android.ext.android.inject

class AddToPlanFragment: Fragment(), OnItemCheckedListener {

    private lateinit var addToPlanViewModel: AddToPlanViewModel
    private var _binding: FragmentAddToPlanBinding? = null

    private val binding get() = _binding!!

    private val selectedItems:MutableSet<PointOfInterest> = mutableSetOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Repository DI
        val repository: Repository by inject()

        setHasOptionsMenu(true)

        addToPlanViewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(AddToPlanViewModel::class.java)

        _binding = FragmentAddToPlanBinding.inflate(inflater, container, false)

        // Recycler View
        val addToPlanAdapter = AddToPlanAdapter(this)
        binding.addToPlanList.adapter = addToPlanAdapter

        addToPlanViewModel.pointsOfInterestNotInPlan.observe(viewLifecycleOwner) { pointsOfInterest ->
            println("View Model List: $pointsOfInterest")
            addToPlanAdapter.submitList(pointsOfInterest)
        }

        // Save Operation Status
        addToPlanViewModel.saveOperationComplete.observe(viewLifecycleOwner) { complete ->
            if(complete) {
                findNavController().navigateUp()
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
            R.id.save_to_plan_button -> {
                addToPlanViewModel.addItemsToPlan(selectedItems.toList())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_to_plan_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onItemCheckChanged(pointOfInterest: PointOfInterest, checkedValue: Boolean) {
        if(checkedValue) {
            selectedItems.add(pointOfInterest)
        } else {
            selectedItems.remove(pointOfInterest)
        }
    }

}