package com.example.assessmentspectrumglobal.database

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import kotlinx.android.parcel.Parcelize

/**
 * Created by kiranb on 4/8/20
 */
@Parcelize
class CompanyWithMembers :Parcelable{
    @Embedded
    var companyEntity: CompanyEntity? = null

    @Relation(
        parentColumn = "id",
        entityColumn = "company_id",
        entity = MemberEntity::class
    )
    var members: List<MemberEntity>? = emptyList()

    companion object{
        fun toCompanyWithMembers(data:ClubDataModel): CompanyWithMembers {
            return CompanyWithMembers().apply {
                companyEntity = CompanyEntity(data._id!!,about = data.about,company = data.company,website = data.website, logo = data.logo)
                members = data.members?.mapNotNull { it -> MemberEntity(memberID = it!!._id!!, cId = data._id!!,email = it.email,name = "${it.name?.first} ${it.name?.last}",age = it.age
                ,phone = it.phone)}
            }
        }
    }
}