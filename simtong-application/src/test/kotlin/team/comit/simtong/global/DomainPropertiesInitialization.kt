package team.comit.simtong.global

import team.comit.simtong.global.DomainPropertiesPrefix.AUTHCODELIMIT_EXP
import team.comit.simtong.global.DomainPropertiesPrefix.AUTHCODELIMIT_MAX_ATTEMPT_COUNT
import team.comit.simtong.global.DomainPropertiesPrefix.AUTHCODELIMIT_VERIFIED_EXP
import team.comit.simtong.global.DomainPropertiesPrefix.AUTHCODE_EXP
import team.comit.simtong.global.DomainPropertiesPrefix.HEAD_SHOP
import team.comit.simtong.global.DomainPropertiesPrefix.USER_DEFAULT_IMAGE
import team.comit.simtong.global.DomainPropertiesPrefix.WEEK_HOLIDAY_LIMIT

object DomainPropertiesInitialization {
    init {
        DomainProperties.putAll(
            mapOf(
                Pair(USER_DEFAULT_IMAGE, "test"),
                Pair(AUTHCODE_EXP, "12345"),
                Pair(AUTHCODELIMIT_EXP, "12345"),
                Pair(AUTHCODELIMIT_VERIFIED_EXP, "12345"),
                Pair(AUTHCODELIMIT_MAX_ATTEMPT_COUNT, "12345"),
                Pair(HEAD_SHOP, "test"),
                Pair(WEEK_HOLIDAY_LIMIT, "12345")
            )
        )
    }
}