package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.domain.auth.exception.UsedEmailException
import team.comit.simtong.domain.auth.spi.QueryAuthCodeLimitPort
import team.comit.simtong.domain.user.dto.ChangeEmailRequest
import team.comit.simtong.domain.user.exception.UserNotFoundException
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
    private val userSecurityPort: UserSecurityPort,
    private val queryAuthCodeLimitPort: QueryAuthCodeLimitPort,
    private val commandUserPort: CommandUserPort
) {

    fun execute(request: ChangeEmailRequest) {
        val authCodeLimit = queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(request.email)

        if (authCodeLimit?.isVerified != true) {
            throw UncertifiedEmailException.EXCEPTION
        }

        if (queryUserPort.existsUserByEmail(request.email)) {
            throw UsedEmailException.EXCEPTION
        }

        val currentUserId = userSecurityPort.getCurrentUserId()
        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserNotFoundException.EXCEPTION

        commandUserPort.save(
            user.copy(
                email = request.email
            )
        )
    }

}