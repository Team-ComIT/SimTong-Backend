package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.dto.request.ChangePasswordRequest
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
 * @version 1.0.0
 **/
@UseCase
class ChangePasswordUseCase(
    private val queryUserPort: QueryUserPort,
    private val userSecurityPort: UserSecurityPort,
    private val commandUserPort: CommandUserPort
) {

    fun execute(request: ChangePasswordRequest) {
        val currentUserId = userSecurityPort.getCurrentUserId()
        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()

        if (!userSecurityPort.compare(request.password, user.password)) {
            throw UserExceptions.DifferentPassword()
        }

        commandUserPort.save(
            user.copy(
                password = userSecurityPort.encode(request.newPassword)
            )
        )
    }

}