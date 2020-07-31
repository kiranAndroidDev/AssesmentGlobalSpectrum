package com.example.assessmentspectrumglobal.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}