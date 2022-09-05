package team.comit.simtong.domain.user.spi

/**
 *
 * User의 Email 인증 정책을 관리하는 CheckEmailPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/05
 * @version 1.0.0
 **/
interface CheckEmailPort {

    fun checkUsedEmail(email: String)

    fun checkCertifiedEmail(email: String)

}