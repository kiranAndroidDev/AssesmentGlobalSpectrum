package com.example.assessmentspectrumglobal.di

import androidx.room.Room
import com.example.assessmentspectrumglobal.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
Created by kiranb on 4/8/20
 */
val dbModule = module {

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java,
            "club-db").fallbackToDestructiveMigration().build()
    }

    single { get<AppDatabase>().companyDAO() }

}