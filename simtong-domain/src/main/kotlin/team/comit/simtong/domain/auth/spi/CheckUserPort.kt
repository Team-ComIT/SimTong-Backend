package team.comit.simtong.domain.auth.spi

/**
 *
 * User 정보를 확인하는 CheckUserPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/09
 * @version 1.0.0
 **/
interface CheckUserPort {

    fun existsUserByEmail(email: String): Boolean

}