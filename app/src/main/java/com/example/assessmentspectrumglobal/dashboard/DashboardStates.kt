package com.example.assessmentspectrumglobal.dashboard

import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.CompanyWithMembers

/**
Created by kiranb on 30/7/20
 */
sealed class DashboardStates {
    data class Error(var msg: String?) : DashboardStates()
    object Loading : DashboardStates()
    data class Success(var data: List<CompanyWithMembers>?) : DashboardStates()
}