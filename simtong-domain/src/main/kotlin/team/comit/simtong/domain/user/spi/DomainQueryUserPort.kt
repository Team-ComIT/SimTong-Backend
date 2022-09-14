package team.comit.simtong.domain.user.spi

/**
 *
 * Domain에서 User에 관한 Query를 요청하는 DomainQueryUserPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/09
 * @version 1.0.0
 **/
interface DomainQueryUserPort {

    fun existsUserByEmail(email: String): Boolean

}