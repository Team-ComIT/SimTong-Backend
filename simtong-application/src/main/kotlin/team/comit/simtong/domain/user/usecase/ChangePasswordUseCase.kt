package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.dto.ChangePasswordRequest
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 사용자의 비밀번호 변경을 담당하는 ChangePasswordUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/10/14
 * @version 1.2.5
 **/
@UseCase
class ChangePasswordUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: UserSecurityPort,
    private val commandUserPort: CommandUserPort
) {

    fun execute(request: ChangePasswordRequest) {
        val currentUserId = securityPort.getCurrentUserId()
        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()

        if (!securityPort.compare(request.password, user.password.value)) {
            throw UserExceptions.DifferentPassword()
        }

        commandUserPort.save(
            user.changePassword(
                password = securityPort.encode(request.newPassword)
            )
        )
    }
}