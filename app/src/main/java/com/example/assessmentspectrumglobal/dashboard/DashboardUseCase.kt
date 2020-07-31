package com.example.assessmentspectrumglobal.dashboard

import android.util.Log
import com.example.assessmentspectrumglobal.utils.Status

/**
Created by kiranb on 30/7/20
 */
class DashboardUseCase( val repo: DashboardContract.IRepo) : DashboardContract.ILogic {
    override suspend fun getClubData(): DashboardStates {
        val res = repo.getClubData()
        return when (res.status) {
            Status.SUCCESS -> DashboardStates.Success(res.data)
            else -> DashboardStates.Error(res.message)
        }
    }
}