package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.dto.response.TokenResponse
import team.comit.simtong.domain.user.dto.request.AdminSignInData
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserJwtPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 관리자의 로그인 기능을 담당하는 AdminSignInUseCase
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/10/04
 * @version 1.2.5
 **/
@UseCase
class AdminSignInUseCase(
    private val queryUserPort: QueryUserPort,
    private val jwtPort: UserJwtPort,
    private val securityPort: UserSecurityPort
) {

    fun execute(request: AdminSignInData): TokenResponse {
        val admin = queryUserPort.queryUserByEmployeeNumber(request.employeeNumber)
            ?.apply { this.checkAuthority(Authority.ROLE_ADMIN) }
            ?: throw UserExceptions.NotFound("관리자가 존재하지 않습니다.")

        if (!securityPort.compare(request.password, admin.password.value)) {
            throw UserExceptions.DifferentPassword()
        }

        return jwtPort.receiveToken(
            userId = admin.id,
            authority = admin.authority
        )
    }
}