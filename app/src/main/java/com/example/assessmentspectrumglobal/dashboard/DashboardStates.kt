package com.example.assessmentspectrumglobal.dashboard

import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.CompanyWithMembers
import com.example.assessmentspectrumglobal.database.MemberEntity

/**
Created by kiranb on 30/7/20
 */
sealed class DashboardStates {
    data class Error(var msg: String?) : DashboardStates()
    object Loading : DashboardStates()
    data class CompanyWithMemberSuccess(var data: List<CompanyWithMembers>?) : DashboardStates()
    data class MemberDataSuccess(var data: List<MemberEntity>?) : DashboardStates()
}