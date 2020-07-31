package com.example.assessmentspectrumglobal.dashboard

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessmentspectrumglobal.R
import com.example.assessmentspectrumglobal.dashboard.adapter.ClubDataListAdapter
import com.example.assessmentspectrumglobal.dashboard.base.BaseActivity
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.databinding.ActivityDashboardBinding
import org.koin.android.viewmodel.ext.android.viewModel


class DashboardActivity : BaseActivity(), DashboardContract.IView {
    private var adapter: ClubDataListAdapter? = null
    private val dashboardViewModel: DashboardViewModel by viewModel()
    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Dashboard"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

        /*binding.sortAscending.setOnClickListener { adapter?.showSortedList(true) }
        binding.sortDescending.setOnClickListener { adapter?.showSortedList(false) }*/

        dashboardViewModel.subscribeToState()?.observe(this, Observer { state ->
            when (state) {
                is DashboardStates.Success -> {
                    showLoading(false)
                    state.data?.let {
                        initAdapter(state.data!!)
                        setSpinner()
                    }
                }
                is DashboardStates.Error -> {
                    showLoading(false)
                    showErrorMessage(state.msg)
                }
                is DashboardStates.Loading -> showLoading(true)
            }
        })
        dashboardViewModel.getClubData()
    }

    private fun setSpinner() {
        binding.textSort.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arrayListOf("Sort By", "Ascending", "Descending")
        )
        binding.textSort.setSelection(0)
        binding.textSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                adapter?.showOrignalList()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> adapter?.showOrignalList()
                    1 -> adapter?.showSortedList(true)
                    2 -> adapter?.showSortedList(false)
                }
            }

        }
    }

    override fun initAdapter(list: List<ClubDataModel>) {
        binding.rV.layoutManager = LinearLayoutManager(this)
        adapter = ClubDataListAdapter(list as ArrayList<ClubDataModel>)
        binding.rV.adapter = adapter
    }

    override fun showLoading(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
    }


}