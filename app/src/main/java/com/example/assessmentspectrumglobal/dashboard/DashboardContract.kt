package com.example.assessmentspectrumglobal.dashboard

import androidx.lifecycle.LiveData
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.utils.Resource

/**
Created by kiranb on 30/7/20
 */
interface DashboardContract {
    interface IView {
        fun showLoading(show: Boolean)
        fun loadMemberScene(list: List<ClubDataModel.Member?>)
        fun loadClubListScene(list: List<ClubDataModel>)
    }

    interface IClubDataFragmentView {
        fun initClubListAdapter(list: List<ClubDataModel>)
    }

    interface IMemberDataragmentView {
        fun initMemberListAdapter(list: List<ClubDataModel.Member>)
    }

    interface IActions {
        fun getClubData()
        fun subscribeToState(): LiveData<DashboardStates>?
    }

    interface ILogic {
        suspend fun getClubData(): DashboardStates
    }

    interface IRepo {
        suspend fun getClubDataRemote(): Resource<List<CompanyEntity>>
        suspend fun getClubData(): Resource<List<CompanyEntity>>
    }
}