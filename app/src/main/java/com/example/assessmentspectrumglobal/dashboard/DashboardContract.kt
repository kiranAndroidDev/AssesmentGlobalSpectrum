package com.example.assessmentspectrumglobal.dashboard

import androidx.lifecycle.LiveData
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.CompanyWithMembers
import com.example.assessmentspectrumglobal.database.MemberEntity
import com.example.assessmentspectrumglobal.utils.Resource

/**
Created by kiranb on 30/7/20
 */
interface DashboardContract {
    interface IView {
        fun showLoading(show: Boolean)
        fun loadMemberScene(list: List<MemberEntity>)
        fun loadClubListScene(list: List<CompanyWithMembers>)
    }

    interface IClubDataFragmentView {
        fun initClubListAdapter(list: List<CompanyWithMembers>)
    }

    interface IMemberDataragmentView {
        fun initMemberListAdapter(list: List<MemberEntity>)
    }

    interface IActions {
        fun getClubData()
        fun subscribeToState(): LiveData<DashboardStates>?
        fun updateCompany(companyEntity: CompanyEntity)
        fun updateMember(memberEntity: MemberEntity)
    }

    interface ILogic {
        suspend fun getClubData(): DashboardStates
        suspend fun updateCompany(companyEntity: CompanyEntity)
        suspend fun updateMember(memberEntity: MemberEntity)

    }

    interface IRepo {
        suspend fun getClubDataRemote(): Resource<List<CompanyWithMembers>>
        suspend fun getClubData(): Resource<List<CompanyWithMembers>>
        suspend fun updateCompany(companyEntity: CompanyEntity)
        suspend fun updateMember(memberEntity: MemberEntity)
    }
}