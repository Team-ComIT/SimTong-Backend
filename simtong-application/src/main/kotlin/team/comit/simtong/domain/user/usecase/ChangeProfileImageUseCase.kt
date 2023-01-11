package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.file.exception.FileExceptions
import team.comit.simtong.domain.file.spi.CheckFilePort
import team.comit.simtong.domain.user.dto.request.ChangeProfileImageRequest
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 프로필 사진 변경을 담당하는 ChangeProfileImageUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
@UseCase
class ChangeProfileImageUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: UserSecurityPort,
    private val commandUserPort: CommandUserPort,
    private val checkFilePort: CheckFilePort
) {

    fun execute(request: ChangeProfileImageRequest) {
        if (!checkFilePort.existsPath(request.profileImagePath)) {
            throw FileExceptions.PathNotFound()
        }

        val currentUserId = securityPort.getCurrentUserId()
        val user = queryUserPort.queryUserById(currentUserId) ?: throw UserExceptions.NotFound()

        commandUserPort.save(
            user.copy(
                profileImagePath = request.profileImagePath
            )
        )
    }

}