package com.example.assessmentspectrumglobal.dashboard

import com.example.assessmentspectrumglobal.database.CompanyWithMembersDao
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.MemberDao
import com.example.assessmentspectrumglobal.network.ApiHelper
import com.example.assessmentspectrumglobal.utils.Resource
import com.example.assessmentspectrumglobal.utils.Utility

/**
Created by kiranb on 30/7/20
 */

class DashboardRepository(
    val apiHelper: ApiHelper,
    val utility: Utility,
    val companyWithMembersDao: CompanyWithMembersDao,
    val memberDao: MemberDao
) :
    DashboardContract.IRepo {


    override suspend fun getClubDataRemote(): Resource<List<CompanyEntity>> {
        var list: List<CompanyEntity>
        if (companyWithMembersDao.getAllCompanies().isNullOrEmpty()) {
            val res = apiHelper.getData() ?: return Resource.error(null)
            companyWithMembersDao.saveAll(res.map { CompanyEntity.DataToMap(it) })
        }
        list = companyWithMembersDao.getAllCompanies()
        return if(list.isNullOrEmpty())
            Resource.error(null)
        else
            Resource.success(list)

    }

    /*
    * For instance logic goes as on every session login we will add data to our database
    * and clear database on logout. But we are not using any session maintenance so we will update data
    * only once
    * */
    override suspend fun getClubData(): Resource<List<CompanyEntity>> {
        var list: List<CompanyEntity>
        if (companyWithMembersDao.getAllCompanies().isNullOrEmpty()) {
            val res = utility.getClubDataLocal() ?: return Resource.error(null)
            companyWithMembersDao.saveAll(res.map { CompanyEntity.DataToMap(it) })
            memberDao.saveAll(res.map { CompanyEntity.DataToMap(it) })
        }
        list = companyWithMembersDao.getAllCompanies()
        return if(list.isNullOrEmpty())
            Resource.error(null)
        else
            Resource.success(list)
    }

}

