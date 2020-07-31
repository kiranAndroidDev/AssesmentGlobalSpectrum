package com.example.assessmentspectrumglobal.dashboard

import androidx.lifecycle.LiveData
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.utils.Resource

/**
Created by kiranb on 30/7/20
 */
interface DashboardContract {
    interface IView{
        fun showLoading(show: Boolean)
        fun initAdapter(list: List<ClubDataModel>)
    }
    interface IActions{
        fun getClubData()
        fun subscribeToState(): LiveData<DashboardStates>?
    }
    interface ILogic{
        suspend fun getClubData(): DashboardStates
    }
    interface IRepo{
        suspend fun getClubData(): Resource<List<ClubDataModel>>
    }
}