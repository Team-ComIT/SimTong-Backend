package team.comit.simtong.global

/**
 *
 * Domain Properties의 Key를 관리하는 DomainPropertiesPrefix
 *
 * @author Chokyunghyeon
 * @date 2022/10/29
 * @version 1.0.0
 **/
object DomainPropertiesPrefix {

    // User
    const val USER_DEFAULT_IMAGE = "user.default-image"

    // AuthCode
    const val AUTHCODE_EXP = "authcode.exp"

    // AuthCodeLimit
    const val AUTHCODELIMIT_EXP = "authcodelimit.exp"
    const val AUTHCODELIMIT_VERIFIED_EXP = "authcodelimit.verified-exp"
    const val AUTHCODELIMIT_MAX_ATTEMPT_COUNT = "authcodelimit.max-attempt-count"

}