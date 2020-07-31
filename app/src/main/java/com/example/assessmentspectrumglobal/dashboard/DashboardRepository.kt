package com.example.assessmentspectrumglobal.dashboard

import com.example.assessmentspectrumglobal.R
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.network.ApiHelper
import com.example.assessmentspectrumglobal.utils.Resource
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import kotlin.Exception

/**
Created by kiranb on 30/7/20
 */
class DashboardRepository( val apiHelper: ApiHelper) : DashboardContract.IRepo {
    override suspend fun getClubData(): Resource<List<ClubDataModel>> {
        return try {
            Resource.success(apiHelper.getData())
        } catch (e: HttpException) {
            Resource.error(e.localizedMessage)
        } catch (e: Exception) {
            Resource.error(null)
        }

    }
}