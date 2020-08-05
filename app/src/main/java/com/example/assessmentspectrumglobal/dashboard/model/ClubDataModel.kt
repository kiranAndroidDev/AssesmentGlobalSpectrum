package com.example.assessmentspectrumglobal.dashboard.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize


data class ClubDataModel(
    var about: String?,
    var company: String?,
    var _id: String?,
    var logo: String?,
    var members: List<Member?>?,
    var website: String?
) {
    data class Member(
        var age: Int?,
        var email: String?,
        var _id: String?,
        var name: Name?,
        var phone: String?
    ) {
        data class Name(
            var first: String?,
            var last: String?
        )
    }
}
