package com.example.assessmentspectrumglobal.dashboard

import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.MemberEntity

/**
Created by kiranb on 4/8/20
 */
interface ItemSelection {
        fun onShowMembers(companyId:String,companyName:String)
        fun onMarkCompanyFavourite(companyEntity: CompanyEntity)
        fun onFollowCompany(companyEntity: CompanyEntity)
        fun onMarkMemberFavourite(memberEntity: MemberEntity)
}