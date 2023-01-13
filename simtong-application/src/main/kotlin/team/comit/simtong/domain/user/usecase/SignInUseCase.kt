package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.user.dto.UserSignInRequest
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.DeviceToken
import team.comit.simtong.domain.user.spi.CommandDeviceTokenPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserJwtPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 사용자의 로그인 기능을 담당하는 SignInUseCase
 *
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.2.5
 **/
@UseCase
class SignInUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: UserSecurityPort,
    private val jwtPort: UserJwtPort,
    private val commandDeviceTokenPort: CommandDeviceTokenPort
) {

    fun execute(request: UserSignInRequest): TokenResponse {
        val employee = queryUserPort.queryUserByEmployeeNumber(request.employeeNumber)
            ?.apply { checkAuthority(Authority.ROLE_COMMON) }
            ?: throw UserExceptions.NotFound()

        if (!securityPort.compare(request.password, employee.password.value)) {
            throw UserExceptions.DifferentPassword()
        }

        commandDeviceTokenPort.save(
            DeviceToken.of(
                userId = employee.id,
                token = request.deviceToken
            )
        )

        return jwtPort.receiveToken(
            userId = employee.id,
            authority = Authority.ROLE_COMMON
        )
    }
}