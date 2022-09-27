package team.comit.simtong.domain.user.spi

import java.util.UUID

/**
 *
 * User와 관한 보안 처리를 요청하는 UserSecurityPort
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/04
 * @version 1.0.0
 **/
interface UserSecurityPort {

    fun compare(target: String, encryptedPassword: String): Boolean

    fun encode(password: String): String

    fun getCurrentUserId(): UUID

}