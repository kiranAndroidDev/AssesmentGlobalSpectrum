package com.example.assessmentspectrumglobal.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.MemberEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Created by kiranb on 30/7/20
 */
class DashboardViewModel(var logic: DashboardContract.ILogic) : ViewModel(), DashboardContract.IActions {
    private val dashboardStates = MutableLiveData<DashboardStates>()

    override fun subscribeToState(): LiveData<DashboardStates>? = dashboardStates

    override fun getClubData() {
        dashboardStates.postValue(DashboardStates.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            dashboardStates.postValue(logic.getClubData())
        }
    }

    override  fun updateCompany(companyEntity: CompanyEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            logic.updateCompany(companyEntity)
        }
    }

    override  fun updateMember(memberEntity: MemberEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            logic.updateMember(memberEntity)
        }
    }

    override fun loadMembers(companyId: String) {
        dashboardStates.postValue(DashboardStates.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            dashboardStates.postValue(logic.loadMembers(companyId))
        }
    }
}