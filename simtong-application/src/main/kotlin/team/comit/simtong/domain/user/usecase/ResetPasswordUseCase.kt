package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.user.dto.ResetPasswordRequest
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserCommandAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserQueryAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 사용자의 비밀번호 초기화를 담당하는 ResetPasswordUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/27
 * @version 1.2.5
 **/
@UseCase
class ResetPasswordUseCase(
    private val queryUserPort: QueryUserPort,
    private val queryAuthCodeLimitPort: UserQueryAuthCodeLimitPort,
    private val commandAuthCodeLimitPort: UserCommandAuthCodeLimitPort,
    private val commandUserPort: CommandUserPort,
    private val securityPort: UserSecurityPort
) {

    fun execute(request: ResetPasswordRequest) {
        val authCodeLimit = queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(request.email)
            ?: throw AuthExceptions.RequiredNewEmailAuthentication()

        if (!authCodeLimit.verified) {
            throw AuthExceptions.UncertifiedEmail()
        }

        val user = queryUserPort.queryUserByEmailAndEmployeeNumber(request.email, request.employeeNumber)
            ?: throw UserExceptions.NotFound()

        commandUserPort.save(
            user.changePassword(
                password = securityPort.encode(request.newPassword)
            )
        )

        commandAuthCodeLimitPort.delete(authCodeLimit)
    }
}