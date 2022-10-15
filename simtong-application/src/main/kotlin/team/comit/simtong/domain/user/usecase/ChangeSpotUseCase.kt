package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.spot.exception.SpotNotFoundException
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase
import java.util.*

@UseCase
class ChangeSpotUseCase(
    private val queryUserPort: QueryUserPort,
    private val commandUserPort: CommandUserPort,
    private val querySpotPort: UserQuerySpotPort,
    private val securityPort: UserSecurityPort
) {

    fun execute(newSpotId: UUID) {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserNotFoundException.EXCEPTION

        if (!querySpotPort.existsSpotById(newSpotId)) {
            throw SpotNotFoundException.EXCEPTION
        }

        commandUserPort.save(
            user.copy(
                spotId = newSpotId
            )
        )
    }

}