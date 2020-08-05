package com.example.assessmentspectrumglobal.dashboard.fragments

/**
Created by kiranb on 2/8/20
 */

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessmentspectrumglobal.R
import com.example.assessmentspectrumglobal.dashboard.DashboardActivity
import com.example.assessmentspectrumglobal.dashboard.DashboardContract
import com.example.assessmentspectrumglobal.dashboard.adapter.ClubDataListAdapter
import com.example.assessmentspectrumglobal.dashboard.ItemSelection
import com.example.assessmentspectrumglobal.database.CompanyWithMembers
import com.example.assessmentspectrumglobal.databinding.FragmentClubListBinding
import java.util.*

private const val ARG_PARAM1 = "companyList"

class ClubDataListFragment : Fragment(),
    DashboardContract.IClubDataFragmentView {
    private  var listener: ItemSelection?=null
    private var clubDataListAdapter: ClubDataListAdapter? = null
    private var dataList: List<CompanyWithMembers>? = null
    lateinit var binding: FragmentClubListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataList = it.getParcelableArrayList(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_club_list, container, false)
        initClubListAdapter(dataList!!)
        setSpinnerForClubDataList()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: List<CompanyWithMembers>) =
            ClubDataListFragment()
                .apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1 as ArrayList)
                }
            }
    }

    private fun setSpinnerForClubDataList() {
        binding.spinnerSort.adapter = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                arrayListOf("Sort By", "Ascending", "Descending")
            )
        }
        binding.spinnerSort.setSelection(0)
        binding.spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                clubDataListAdapter?.showOriginalsList()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 ->  clubDataListAdapter?.showOriginalsList()
                    1 -> clubDataListAdapter?.showSortedList(true)
                    2 ->  clubDataListAdapter?.showSortedList(false)
                }
            }

        }
    }

    override fun initClubListAdapter(list: List<CompanyWithMembers>) {
        binding.rV.layoutManager = LinearLayoutManager(activity)
        binding.rV.setHasFixedSize(true)
        clubDataListAdapter = ClubDataListAdapter(list, (activity as DashboardActivity))
        binding.rV.adapter = clubDataListAdapter
    }

}