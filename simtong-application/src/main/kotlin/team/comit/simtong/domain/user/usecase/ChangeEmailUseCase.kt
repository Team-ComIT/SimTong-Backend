package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.auth.spi.QueryAuthCodeLimitPort
import team.comit.simtong.domain.user.dto.request.ChangeEmailData
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 이메일 변경을 담당하는 ChangeEmailUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
@UseCase
class ChangeEmailUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: UserSecurityPort,
    private val queryAuthCodeLimitPort: QueryAuthCodeLimitPort,
    private val commandUserPort: CommandUserPort
) {

    fun execute(request: ChangeEmailData) {
        if (queryUserPort.existsUserByEmail(request.email)) {
            throw AuthExceptions.AlreadyUsedEmail()
        }

        val authCodeLimit = queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(request.email)
            ?: throw AuthExceptions.RequiredNewEmailAuthentication()

        if (!authCodeLimit.verified) {
            throw AuthExceptions.UncertifiedEmail()
        }

        val currentUserId = securityPort.getCurrentUserId()
        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()

        commandUserPort.save(
            user.copy(
                email = request.email
            )
        )
    }

}