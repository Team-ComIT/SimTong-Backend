package team.comit.simtong.global.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import team.comit.simtong.global.DomainPropertiesKey
import java.lang.System.setProperty
import javax.annotation.PostConstruct

@ConfigurationProperties("domain")
@ConstructorBinding
class DomainProperties(
    val user: UserProperties,
    val authCode: AuthCodeProperties,
    val authCodeLimit: AuthCodeLimitProperties
) {

    class UserProperties(
        val defaultImage: String
    )

    class AuthCodeProperties(
        val expired: String
    )

    class AuthCodeLimitProperties(
        val expired: String,
        val verifiedExpired: String,
        val maxAttemptCount: String
    )

    @PostConstruct
    fun setProperties() {
        setProperty(DomainPropertiesKey.USER_DEFAULT_IMAGE, user.defaultImage)
        setProperty(DomainPropertiesKey.AUTHCODE_EXPIRED, authCode.expired)
        setProperty(DomainPropertiesKey.AUTHCODELIMIT_EXPIRED, authCodeLimit.expired)
        setProperty(DomainPropertiesKey.AUTHCODELIMIT_VERIFIED_EXPIRED, authCodeLimit.verifiedExpired)
        setProperty(DomainPropertiesKey.AUTHCODELIMIT_MAX_ATTEMPT_COUNT, authCodeLimit.maxAttemptCount)
    }

}