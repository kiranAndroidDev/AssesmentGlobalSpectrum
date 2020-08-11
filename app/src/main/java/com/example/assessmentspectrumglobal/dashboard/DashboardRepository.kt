package com.example.assessmentspectrumglobal.dashboard

import android.util.Log
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.CompanyWithMembersDao
import com.example.assessmentspectrumglobal.database.CompanyWithMembers
import com.example.assessmentspectrumglobal.database.MemberEntity
import com.example.assessmentspectrumglobal.network.ApiHelper
import com.example.assessmentspectrumglobal.utils.Resource
import com.example.assessmentspectrumglobal.utils.Utility

/**
Created by kiranb on 30/7/20
 */

class DashboardRepository(
    val apiHelper: ApiHelper,
    val utility: Utility,
    val companyWithMembersDao: CompanyWithMembersDao
) :
    DashboardContract.IRepo {


    override suspend fun getClubDataRemote(): Resource<List<CompanyWithMembers>> {
        if (companyWithMembersDao.loadAll().isNullOrEmpty()) {
            val res = apiHelper.getData()
            for (item in res) {
                companyWithMembersDao.insert(CompanyWithMembers.toCompanyWithMembers(item))
            }
        }
        val list: List<CompanyWithMembers> = companyWithMembersDao.loadAll()
        return if (list.isNullOrEmpty())
            Resource.error(null)
        else
            Resource.success(list)

    }

    /*
    * For instance logic goes as on every session login we will add data to our database
    * and clear database on logout. But we are not using any session maintenance so we will update data
    * only once
    * */
    override suspend fun getClubData(): Resource<List<CompanyWithMembers>> {
        if (companyWithMembersDao.loadAll().isNullOrEmpty()) {
            val res = utility.getClubDataLocal() ?: return Resource.error(null)
            Log.e("res", "$res")
            for (item in res) {
                companyWithMembersDao.insert(CompanyWithMembers.toCompanyWithMembers(item))
            }
        }
        var list: List<CompanyWithMembers> = companyWithMembersDao.loadAll()
        return if (list.isNullOrEmpty())
            Resource.error(null)
        else
            Resource.success(list)
    }

    override suspend fun updateCompany(companyEntity: CompanyEntity) {
        companyWithMembersDao.updateCompany(companyEntity)
    }

    override suspend fun updateMember(memberEntity: MemberEntity) {
        companyWithMembersDao.updateMember(memberEntity)
    }

    override suspend fun loadMembers(companyId: String):Resource<List<MemberEntity>> {
       val res =  companyWithMembersDao.loadMembers(companyId)
        return if(res==null)
            Resource.error(null)
        else
            Resource.success(res)
    }

}

