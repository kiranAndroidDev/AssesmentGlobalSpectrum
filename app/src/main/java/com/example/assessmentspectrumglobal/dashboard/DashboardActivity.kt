package com.example.assessmentspectrumglobal.dashboard

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessmentspectrumglobal.R
import com.example.assessmentspectrumglobal.dashboard.base.BaseActivity
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.databinding.ActivityDashboardBinding
import org.koin.android.viewmodel.ext.android.viewModel


class DashboardActivity : BaseActivity(), DashboardContract.IView {
    private val dashboardViewModel: DashboardViewModel by viewModel()
    lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)

        dashboardViewModel.subscribeToState()?.observe(this, Observer { state ->
            when (state) {
                is DashboardStates.Success -> {
                    showLoading(false)
                    state.data?.let { initAdapter(state.data!!) }
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

    override fun initAdapter(list: List<ClubDataModel>) {
        binding.rV.layoutManager = LinearLayoutManager(this)
        val adapter = ClubDataListAdapter(this,list)
        binding.rV.adapter = adapter
    }

    override fun showLoading(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
    }


}