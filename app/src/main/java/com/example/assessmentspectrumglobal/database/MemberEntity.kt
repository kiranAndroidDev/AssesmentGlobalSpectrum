package com.example.assessmentspectrumglobal.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by kiranb on 4/8/20
 */
@Parcelize
@Entity( tableName = "member"
)
class MemberEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var memberID:String,
    @ColumnInfo(name = "company_id")
    var cId:String?,
    var age: Int?,
    var email: String?,
    var name: String,
    var phone: String?
):Parcelable{}
/*
* foreignKeys = [ForeignKey(
        entity = CompanyEntity::class,
        parentColumns = ["id"],
        childColumns = ["company_id"],
        onDelete = ForeignKey.CASCADE
    )]*/