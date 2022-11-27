package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.user.dto.SignInRequest
import team.comit.simtong.domain.user.exception.DifferentPasswordException
import team.comit.simtong.domain.user.exception.DifferentPermissionAccountException
import team.comit.simtong.domain.user.exception.UserNotFoundException
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
 * @date 2022/10/04
 * @version 1.0.0
 **/
@UseCase
class AdminSignInUseCase(
    private val queryUserPort: QueryUserPort,
    private val userJwtPort: UserJwtPort,
    private val userSecurityPort: UserSecurityPort
) {

    fun execute(request: SignInRequest): TokenResponse {
        val admin = queryUserPort.queryUserByEmployeeNumber(request.employeeNumber)
            ?: throw UserNotFoundException.EXCEPTION

        if (Authority.ROLE_COMMON == admin.authority) {
            throw DifferentPermissionAccountException.EXCEPTION
        }

        if (!userSecurityPort.compare(request.password, admin.password)) {
            throw DifferentPasswordException.EXCEPTION
        }

        return userJwtPort.receiveToken(
            userId = admin.id,
            authority = admin.authority
        )
    }

}