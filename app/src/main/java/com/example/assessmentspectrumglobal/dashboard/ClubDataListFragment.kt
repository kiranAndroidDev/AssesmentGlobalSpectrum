package com.example.assessmentspectrumglobal.dashboard

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
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessmentspectrumglobal.R
import com.example.assessmentspectrumglobal.dashboard.adapter.ClubDataListAdapter
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.databinding.FragmentClubListBinding

private const val ARG_PARAM1 = "memberList"

class ClubDataListFragment : Fragment(), DashboardContract.IClubDataFragmentView {
    private  var clubDataListAdapter: ClubDataListAdapter?=null
    private var dataList: List<ClubDataModel>? = null
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataList?.let { initClubListAdapter(it) }
        setSpinnerForClubDataList()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<ClubDataModel>) =
            ClubDataListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1)
                }
            }
    }

    private fun setSpinnerForClubDataList() {
        binding.spinnerSort?.adapter = ArrayAdapter(
            activity!!,
            android.R.layout.simple_spinner_item,
            arrayListOf("Sort By", "Ascending", "Descending")
        )
        binding.spinnerSort?.setSelection(0)
        binding.spinnerSort?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                clubDataListAdapter?.showOrignalList()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> clubDataListAdapter?.showOrignalList()
                    1 -> clubDataListAdapter?.showSortedList(true)
                    2 -> clubDataListAdapter?.showSortedList(false)
                }
            }

        }
    }

    override fun initClubListAdapter(list: List<ClubDataModel>) {
        binding.rV.layoutManager = LinearLayoutManager(activity)
        clubDataListAdapter = ClubDataListAdapter(list as ArrayList<ClubDataModel>,
            object : ClubDataListAdapter.ItemClickListener {
                override fun showMemberListOnItemSelect(item: ClubDataModel) {
                    item.members?.let {

                    }
                }
            })
        clubDataListAdapter?.setHasStableIds(false)
        binding.rV.adapter = clubDataListAdapter
    }

}