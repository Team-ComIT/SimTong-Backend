package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.user.dto.ChangeProfileRequest
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 프로필 사진 변경을 담당하는 ChangeProfileUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
@UseCase
class ChangeProfileUseCase(
    private val queryUserPort: QueryUserPort,
    private val userSecurityPort: UserSecurityPort,
    private val commandUserPort: CommandUserPort
) {

    fun execute(request: ChangeProfileRequest) {
        val currentUserId = userSecurityPort.getCurrentUserId()
        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserNotFoundException.EXCEPTION

        commandUserPort.save(
            user.copy(
                profileImagePath = request.profileImagePath
            )
        )
    }

}