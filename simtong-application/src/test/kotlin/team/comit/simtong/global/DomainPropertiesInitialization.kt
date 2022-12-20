package team.comit.simtong.global

import team.comit.simtong.global.DomainPropertiesPrefix.ANNUAL_LEAVE_LIMIT
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
                USER_DEFAULT_IMAGE to "test",
                AUTHCODE_EXP to "12345",
                AUTHCODELIMIT_EXP to "12345",
                AUTHCODELIMIT_VERIFIED_EXP to "12345",
                AUTHCODELIMIT_MAX_ATTEMPT_COUNT to "12345",
                HEAD_SHOP to "test",
                WEEK_HOLIDAY_LIMIT to "12345",
                ANNUAL_LEAVE_LIMIT to "12345"
            )
        )
    }
}