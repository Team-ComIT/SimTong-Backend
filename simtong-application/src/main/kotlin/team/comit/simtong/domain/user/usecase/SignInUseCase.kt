package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.user.dto.request.UserSignInData
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
 * @version 1.0.0
 **/
@UseCase
class SignInUseCase(
    private val queryUserPort: QueryUserPort,
    private val userSecurityPort: UserSecurityPort,
    private val userJwtPort: UserJwtPort,
    private val commandDeviceTokenPort: CommandDeviceTokenPort
) {

    fun execute(request: UserSignInData): TokenResponse {
        val user = queryUserPort.queryUserByEmployeeNumber(request.employeeNumber)
            ?: throw UserExceptions.NotFound()

        if (Authority.ROLE_COMMON != user.authority) {
            throw UserExceptions.DifferentPermissionAccount("사원 계정이 아닙니다.")
        }

        if (!userSecurityPort.compare(request.password, user.password)) {
            throw UserExceptions.DifferentPassword()
        }

        commandDeviceTokenPort.save(
            DeviceToken(
                userId = user.id,
                token = request.deviceToken
            )
        )

        return userJwtPort.receiveToken(
            userId = user.id,
            authority = Authority.ROLE_COMMON
        )
    }

}