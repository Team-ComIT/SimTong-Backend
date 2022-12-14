package team.comit.simtong.global.security.token

/**
 *
 * Jwt의 상수를 관리하는 JwtComponent
 *
 * @author Chokyunghyeon
 * @date 2022/09/01
 * @version 1.0.0
 **/
object JwtComponent {

    const val PREFIX = "Bearer "
    const val HEADER = "Authorization"
    const val AUTHORITY = "authority"
    const val ACCESS = "access"
    const val REFRESH = "refresh"

}