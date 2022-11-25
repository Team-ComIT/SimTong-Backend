package team.comit.simtong.domain

import team.comit.simtong.global.DomainProperties
import team.comit.simtong.global.DomainPropertiesPrefix

/**
 * 가장 처음 테스트되는 클래스에 Import
 * Domain Properties 초기화
 */
class DomainPropertiesInitialization {
    init {
        DomainProperties.putAll(
            mapOf(
                Pair(DomainPropertiesPrefix.USER_DEFAULT_IMAGE, "test"),
                Pair(DomainPropertiesPrefix.AUTHCODE_EXPIRED, "12345"),
                Pair(DomainPropertiesPrefix.AUTHCODELIMIT_EXPIRED, "12345"),
                Pair(DomainPropertiesPrefix.AUTHCODELIMIT_VERIFIED_EXPIRED, "12345"),
                Pair(DomainPropertiesPrefix.AUTHCODELIMIT_MAX_ATTEMPT_COUNT, "12345")
            )
        )
    }
}