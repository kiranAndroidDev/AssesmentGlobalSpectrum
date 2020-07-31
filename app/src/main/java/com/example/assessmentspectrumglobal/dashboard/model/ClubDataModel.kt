package com.example.assessmentspectrumglobal.dashboard.model

data class ClubDataModel(
    var about: String?,
    var company: String?,
    var id: String?,
    var logo: String?,
    var members: List<Member?>?,
    var website: String?
) {
    data class Member(
        var age: Int?,
        var email: String?,
        var id: String?,
        var name: Name?,
        var phone: String?
    ) {
        data class Name(
            var first: String?,
            var last: String?
        )
    }
}
