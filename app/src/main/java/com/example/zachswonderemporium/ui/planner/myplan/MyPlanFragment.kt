package com.example.zachswonderemporium.ui.planner.myplan

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.zachswonderemporium.R
import com.example.zachswonderemporium.data.Repository
import com.example.zachswonderemporium.data.local.ZachsWonderEmporiumDatabase
import com.example.zachswonderemporium.data.model.PlanItemPointOfInterest
import com.example.zachswonderemporium.databinding.FragmentMyPlanBinding
import com.example.zachswonderemporium.ui.ViewModelFactory
import org.koin.android.ext.android.inject

class MyPlanFragment : Fragment(), OnItemClickedListener {

    private lateinit var myPlanViewModel: MyPlanViewModel
    private var _binding: FragmentMyPlanBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Menu
        setHasOptionsMenu(true)

        // Repository DI
        val repository: Repository by inject()

        myPlanViewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(MyPlanViewModel::class.java)

        _binding = FragmentMyPlanBinding.inflate(inflater, container, false)

        // Recycler View
        val myPlanAdapter = MyPlanAdapter(this)
        binding.myPlanList.adapter = myPlanAdapter

        myPlanViewModel.myPlanIems.observe(viewLifecycleOwner) { myPlanItems ->
            myPlanAdapter.submitList(myPlanItems)
        }

        // Navigation
        myPlanViewModel.navigateToAddItems.observe(viewLifecycleOwner) { shouldNavigate ->
            if(shouldNavigate) {
                myPlanViewModel.onNavigationToAddItemsComplete()
                findNavController().navigate(MyPlanFragmentDirections.actionNavigationPlannerToNavigationAddToPlan())
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        myPlanViewModel.refreshPlanItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add_to_plan_button -> myPlanViewModel.setNavigateToAddItems(true)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_plan_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDeleteClicked(pointOfInterest: PlanItemPointOfInterest) {
        myPlanViewModel.deletePlanItem(pointOfInterest.planItem)
    }

}