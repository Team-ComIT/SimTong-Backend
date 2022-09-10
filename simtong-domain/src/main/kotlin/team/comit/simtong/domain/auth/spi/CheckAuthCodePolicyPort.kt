package team.comit.simtong.domain.auth.spi

/**
 *
 * AuthCodePolicy의 정보를 확인하는 CheckAuthCodePolicyPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/09
 * @version 1.0.0
 **/
interface CheckAuthCodePolicyPort {

    fun checkCertifiedEmail(email: String): Boolean

}