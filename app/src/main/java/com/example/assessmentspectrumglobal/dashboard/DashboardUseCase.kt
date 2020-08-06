package com.example.assessmentspectrumglobal.dashboard

import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.MemberEntity
import com.example.assessmentspectrumglobal.utils.Status

/**
Created by kiranb on 30/7/20
 */
class DashboardUseCase( val repo: DashboardContract.IRepo) : DashboardContract.ILogic {
    override suspend fun getClubData(): DashboardStates {
        val res = repo.getClubDataRemote()
        return when (res.status) {
            Status.SUCCESS -> DashboardStates.CompanyWithMemberSuccess(res.data)
            else -> DashboardStates.Error(res.message)
        }
    }

    override suspend fun updateCompany(companyEntity: CompanyEntity) {
        repo.updateCompany(companyEntity)
    }

    override suspend fun updateMember(memberEntity: MemberEntity) {
        repo.updateMember(memberEntity)
    }

    override fun loadMembers(companyId: String): DashboardStates {
        val res = repo.loadMembers(companyId)
        return when(res.status){
            Status.SUCCESS -> DashboardStates.MemberDataSuccess(res.data)
            else -> DashboardStates.Error(res.message)
        }
    }
}