package com.example.assessmentspectrumglobal.dashboard

import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
        supportActionBar?.title = "Dashboard"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setUp()
    }

    private fun setUp() {

        dashboardViewModel.subscribeToState()?.observe(this, Observer { state ->
            when (state) {
                is DashboardStates.Success -> {
                    Log.e("stat", "Success")
                    showLoading(false)
                    state.data?.let {
                        loadClubListScene(state.data!!)
                    }
                }
                is DashboardStates.Error -> {
                    Log.e("stat", "error")

                    showLoading(false)
                    showErrorMessage(state.msg)
                }
                is DashboardStates.Loading ->{ showLoading(true)
                    Log.e("stat", "loading")
                }
                else -> Log.e("stat", "Success")

            }
        })
        dashboardViewModel.getClubData()
    }


    override fun loadClubListScene(list: List<ClubDataModel>) {
        val fragment = ClubDataListFragment.newInstance(list as ArrayList<ClubDataModel>)
        addFragment(fragment)
    }

    override fun loadMemberScene(list: List<ClubDataModel.Member?>) {
      /*  val fragment = ClubDataListFragment.newInstance(list as ArrayList<ClubDataModel>)
        addFragment(fragment)*/
    }

    override fun showLoading(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun addFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment).addToBackStack(null).commit()
    }

}