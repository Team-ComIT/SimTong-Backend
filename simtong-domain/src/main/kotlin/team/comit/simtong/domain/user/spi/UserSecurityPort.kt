package team.comit.simtong.domain.user.spi

/**
 *
 * 비밀번호 암호화를 담당하는 UserSecurityPort
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.0.0
 **/
interface UserSecurityPort {

    fun compare(target: String, encryptedPassword: String): Boolean

    fun encode(password: String): String

}