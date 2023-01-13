package team.comit.simtong.domain.user.usecase

import team.comit.simtong.domain.spot.exception.SpotExceptions
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.UseCase
import java.util.UUID

/**
 *
 * 자신의 지점 변경을 담당하는 ChangeSpotUseCase
 *
 * @author kimbeomjin
 * @date 2022/10/15
 * @version 1.2.5
 **/
@UseCase
class ChangeSpotUseCase(
    private val queryUserPort: QueryUserPort,
    private val commandUserPort: CommandUserPort,
    private val querySpotPort: UserQuerySpotPort,
    private val securityPort: UserSecurityPort
) {

    fun execute(newSpotId: UUID) {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserExceptions.NotFound()

        if (!querySpotPort.existsSpotById(newSpotId)) {
            throw SpotExceptions.NotFound()
        }

        commandUserPort.save(
            user.changeSpot(
                spotId = newSpotId
            )
        )
    }
}