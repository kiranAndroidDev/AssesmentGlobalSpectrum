package com.example.assessmentspectrumglobal.dashboard

import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel

/**
Created by kiranb on 4/8/20
 */
interface onCompanySelected {
        fun onShowMembers(list: List<ClubDataModel.Member?>)
}