package com.example.assessmentspectrumglobal.dashboard

import android.content.Context
import android.content.res.AssetManager
import com.example.assessmentspectrumglobal.R
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.network.ApiHelper
import com.example.assessmentspectrumglobal.utils.Resource
import com.example.assessmentspectrumglobal.utils.Utility
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import kotlin.Exception

/**
Created by kiranb on 30/7/20
 */

class DashboardRepository(val apiHelper: ApiHelper, val utility: Utility) :
    DashboardContract.IRepo {
    override suspend fun getClubData(): Resource<List<ClubDataModel>> {
        return try {
            Resource.success(apiHelper.getData())
        } catch (e: Exception) {
            getClubDataLocally()
        }

    }

    override suspend fun getClubDataLocally(): Resource<List<ClubDataModel>> {
        val res =   utility.getClubDataLocal()
            return if (res==null)
                Resource.error(null)
        else
            Resource.success(res)
    }


}