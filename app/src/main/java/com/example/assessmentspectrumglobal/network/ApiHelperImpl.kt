package com.example.assessmentspectrumglobal.network

import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import kotlinx.coroutines.Deferred

/**
Created by kiranb on 30/7/20
 */
class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getData(): List<ClubDataModel> = apiService.getClubData()
}