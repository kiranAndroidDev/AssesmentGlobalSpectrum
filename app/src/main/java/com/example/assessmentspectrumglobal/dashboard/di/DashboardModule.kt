package com.example.assessmentspectrumglobal.dashboard.di

import com.example.assessmentspectrumglobal.dashboard.DashboardContract
import com.example.assessmentspectrumglobal.dashboard.DashboardRepository
import com.example.assessmentspectrumglobal.dashboard.DashboardUseCase
import com.example.assessmentspectrumglobal.database.CompanyWithMembersDao
import com.example.assessmentspectrumglobal.network.ApiHelper
import com.example.assessmentspectrumglobal.utils.Utility
import org.koin.dsl.module

/**
Created by kiranb on 30/7/20
 */


val dashboardModule = module(override = true) {
    factory { createRepository(get(), get(), get()) }
    factory { createUseCase(get()) }
    single { Utility(get()) }
}


fun createUseCase(
    repo: DashboardContract.IRepo
): DashboardContract.ILogic {
    return DashboardUseCase(repo)
}

fun createRepository(
    helper: ApiHelper,
    utility: Utility,
    companyWithMembersDao: CompanyWithMembersDao
): DashboardContract.IRepo {
    return DashboardRepository(helper, utility, companyWithMembersDao)
}


