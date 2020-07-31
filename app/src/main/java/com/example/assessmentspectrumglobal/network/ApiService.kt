package com.example.assessmentspectrumglobal.network

import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
Created by kiranb on 30/7/20
 */
interface ApiService {
    @GET("json/get/Vk-LhK44U")
    suspend fun getClubData(): List<ClubDataModel>
}