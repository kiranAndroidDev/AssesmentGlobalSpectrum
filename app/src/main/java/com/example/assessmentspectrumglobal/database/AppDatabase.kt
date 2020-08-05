package com.example.assessmentspectrumglobal.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
Created by kiranb on 4/8/20
 */

@Database(entities = [CompanyEntity::class, MemberEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun companyDAO(): CompanyWithMembersDao
}