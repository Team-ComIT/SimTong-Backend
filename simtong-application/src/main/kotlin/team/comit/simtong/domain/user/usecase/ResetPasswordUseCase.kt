package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.exception.RequiredNewEmailAuthenticationException
import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.domain.user.dto.ResetPasswordRequest
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQueryAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 사용자의 비밀번호 초기화를 담당하는 ResetPasswordUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/27
 * @version 1.0.0
 **/
@UseCase
class ResetPasswordUseCase(
    private val queryUserPort: QueryUserPort,
    private val userQueryAuthCodeLimitPort: UserQueryAuthCodeLimitPort,
    private val commandUserPort: CommandUserPort,
    private val userSecurityPort: UserSecurityPort
) {

    fun execute(request: ResetPasswordRequest) {
        val authCodeLimit = userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(request.email)
            ?: throw RequiredNewEmailAuthenticationException.EXCEPTION

        if (!authCodeLimit.isVerified) {
            throw UncertifiedEmailException.EXCEPTION
        }

        val user = queryUserPort.queryUserByEmailAndEmployeeNumber(request.email, request.employeeNumber)
            ?: throw UserNotFoundException.EXCEPTION

        commandUserPort.save(
            user.copy(
                password = userSecurityPort.encode(request.newPassword)
            )
        )
    }
}