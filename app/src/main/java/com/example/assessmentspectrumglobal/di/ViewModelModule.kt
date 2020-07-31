package com.example.assessmentspectrumglobal.di

import com.example.assessmentspectrumglobal.dashboard.DashboardContract
import com.example.assessmentspectrumglobal.dashboard.DashboardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Created by kiranb on 30/7/20
 */
val viewModelsModule = module {
    viewModel { DashboardViewModel(get<DashboardContract.ILogic>()) }
}
