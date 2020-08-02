package com.example.assessmentspectrumglobal.dashboard.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClubDataModel(
    var about: String?,
    var company: String?,
    var id: String?,
    var logo: String?,
    var members: List<Member?>?,
    var website: String?
) : Parcelable {
    @Parcelize
    data class Member(
        var age: Int?,
        var email: String?,
        var id: String?,
        var name: Name?,
        var phone: String?
    ) : Parcelable {
        @Parcelize
        data class Name(
            var first: String?,
            var last: String?
        ) : Parcelable
    }
}
