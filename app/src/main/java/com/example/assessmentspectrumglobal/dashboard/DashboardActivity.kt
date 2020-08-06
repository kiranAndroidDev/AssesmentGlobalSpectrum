package com.example.assessmentspectrumglobal.dashboard

import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.assessmentspectrumglobal.R
import com.example.assessmentspectrumglobal.dashboard.base.BaseActivity
import com.example.assessmentspectrumglobal.dashboard.fragments.ClubDataListFragment
import com.example.assessmentspectrumglobal.dashboard.fragments.MemberListFragment
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.CompanyWithMembers
import com.example.assessmentspectrumglobal.database.MemberEntity
import com.example.assessmentspectrumglobal.databinding.ActivityDashboardBinding
import org.koin.android.viewmodel.ext.android.viewModel


class DashboardActivity : BaseActivity(), DashboardContract.IView, ItemSelection {

    private val dashboardViewModel: DashboardViewModel by viewModel()
    lateinit var binding: ActivityDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setUp()
    }

    private fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setUp() {

        dashboardViewModel.subscribeToState()?.observe(this, Observer { state ->
            when (state) {
                is DashboardStates.CompanyWithMemberSuccess -> {
                    showLoading(false)
                    state.data?.let {
                        loadClubListScene(state.data!!)
                    }
                }
                is DashboardStates.Error -> {
                    showLoading(false)
                    showErrorMessage(state.msg)
                }
                is DashboardStates.Loading -> {
                    showLoading(true)
                }

                is DashboardStates.MemberDataSuccess -> {
                    showLoading(false)
                    state.data?.let { loadMemberScene(it) }
                }
                else -> Log.e("stat", "${state}")

            }
        })
        dashboardViewModel.getClubData()
    }


    override fun loadClubListScene(list: List<CompanyWithMembers>) {
        val fragment = ClubDataListFragment.newInstance(list)
        addFragment(fragment)
    }

    override fun loadMemberScene(list: List<MemberEntity>) {
        val fragment = MemberListFragment.newInstance(list)
        replaceFragment(fragment)
    }

    override fun showLoading(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame, fragment).commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment).addToBackStack(null).commit()
    }

    override fun onShowMembers(companyId: String, companyName: String) {
        dashboardViewModel.loadMembers(companyId)
        setToolbarTitle(companyName)
    }

    override fun onFollowCompany(companyEntity: CompanyEntity) {
        dashboardViewModel.updateCompany(companyEntity)
    }

    override fun onMarkCompanyFavourite(companyEntity: CompanyEntity) {
        dashboardViewModel.updateCompany(companyEntity)
    }

    override fun onMarkMemberFavourite(memberEntity: MemberEntity) {
        dashboardViewModel.updateMember(memberEntity)
    }
}
