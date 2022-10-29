package team.comit.simtong.global

/**
 *
 * Domain Properties의 경로를 관리하는 DomainPropertiesPrefix
 *
 * @author Chokyunghyeon
 * @date 2022/10/29
 * @version 1.0.0
 **/
object DomainPropertiesPrefix {

    // properties file
    const val PROPERTIES_FILE = "domain.properties"

    // User
    const val USER_DEFAULT_IMAGE = "domain.user.default-image"

    // AuthCode
    const val AUTHCODE_EXPIRED = "domain.authcode.expired"

    // AuthCodeLimit
    const val AUTHCODELIMIT_EXPIRED = "domain.authcodelimit.expired"
    const val AUTHCODELIMIT_VERIFIED_EXPIRED = "domain.authcodelimit.verified-expired"
    const val AUTHCODELIMIT_MAX_ATTEMPT_COUNT = "domain.authcodelimit.max-attempt-count"

}