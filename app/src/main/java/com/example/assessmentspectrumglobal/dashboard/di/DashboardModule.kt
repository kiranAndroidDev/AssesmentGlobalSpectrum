package com.example.assessmentspectrumglobal.dashboard.di

import com.example.assessmentspectrumglobal.dashboard.DashboardContract
import com.example.assessmentspectrumglobal.dashboard.DashboardRepository
import com.example.assessmentspectrumglobal.dashboard.DashboardUseCase
import com.example.assessmentspectrumglobal.network.ApiHelper
import com.example.assessmentspectrumglobal.network.ApiHelperImpl
import com.example.assessmentspectrumglobal.network.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

/**
Created by kiranb on 30/7/20
 */


val dashboardModule = module(override = true) {
    factory { createRepository(get()) }
    factory { createUseCase(get()) }
}




fun createUseCase(
    repo: DashboardContract.IRepo
): DashboardContract.ILogic {
    return DashboardUseCase(repo)
}

fun createRepository(helper: ApiHelper): DashboardContract.IRepo {
    return DashboardRepository(helper)
}


