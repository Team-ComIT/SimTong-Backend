package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.dto.request.ChangeNicknameData
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 유저의 닉네임 변경을 담당하는 ChangeNicknameUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.2.5
 **/
@UseCase
class ChangeNicknameUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: UserSecurityPort,
    private val commandUserPort: CommandUserPort
) {

    fun execute(request: ChangeNicknameData) {
        if (queryUserPort.existsUserByNickname(request.nickname)) {
            throw UserExceptions.AlreadyUsedNickname()
        }

        val currentUserId = securityPort.getCurrentUserId()
        val employee = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()

        commandUserPort.save(
            employee.changeNickname(request.nickname)
        )
    }
}