package com.example.assessmentspectrumglobal.dashboard.fragments

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
import com.example.assessmentspectrumglobal.dashboard.DashboardContract
import com.example.assessmentspectrumglobal.dashboard.adapter.MemberListAdapter
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.databinding.FragmentMemberListBinding

private const val ARG_PARAM1 = "memberList"

class MemberListFragment : Fragment() ,
    DashboardContract.IMemberDataragmentView {
    private var memberDataListAdapter: MemberListAdapter? = null
    private var memberList: List<ClubDataModel.Member>? = null
    lateinit var binding: FragmentMemberListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            memberList = it.getParcelableArrayList(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)
        initMemberListAdapter(memberList!!)
        setSpinnerForMemberList(binding.spinnerSort)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<ClubDataModel.Member>) =
            MemberListFragment()
                .apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1)
                }
            }
    }

    private fun setSpinnerForMemberList(spinnerSort: AppCompatSpinner?) {
        spinnerSort?.adapter = ArrayAdapter(
            activity!!,
            android.R.layout.simple_spinner_item,
            arrayListOf(
                "Sort By",
                "Name Ascending",
                "Name Descending",
                "Age Ascending",
                "Age Descending"
            )
        )
        spinnerSort?.setSelection(0)
        spinnerSort?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                memberDataListAdapter?.showOrignalList()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> memberDataListAdapter?.showOrignalList()
                    1 -> memberDataListAdapter?.showSortedListByName(true)
                    2 -> memberDataListAdapter?.showSortedListByName(false)
                    3 -> memberDataListAdapter?.showSortedListByAge(true)
                    4 -> memberDataListAdapter?.showSortedListByAge(false)
                }
            }

        }
    }

    override fun initMemberListAdapter(list: List<ClubDataModel.Member>) {
        binding.rV.layoutManager = LinearLayoutManager(activity)
        memberDataListAdapter = MemberListAdapter(list as ArrayList<ClubDataModel.Member>)
        binding.rV.adapter = memberDataListAdapter
    }
}