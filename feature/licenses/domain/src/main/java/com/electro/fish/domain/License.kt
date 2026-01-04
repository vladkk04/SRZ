package com.electro.fish.domain

enum class LicenceType {
    Fishing,
    Ticket,
    MembershipMark
}

interface License {
    val id: Int
    val licenceType: LicenceType

    data class Default(
        override val id: Int,
        override val licenceType: LicenceType
    ) : License
}
