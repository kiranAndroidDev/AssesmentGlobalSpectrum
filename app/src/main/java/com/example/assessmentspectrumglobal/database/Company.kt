package com.example.assessmentspectrumglobal.database

import androidx.annotation.BoolRes
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel

/**
Created by kiranb on 4/8/20
 */
@Entity(tableName = "company")
data class CompanyEntity(
    @PrimaryKey
    val id: String,
    var about: String?,
    var company: String?,
    var logo: String?,
    var website: String?,
    var follow: Boolean = false,
    var favourite: Boolean = false
) {
}
