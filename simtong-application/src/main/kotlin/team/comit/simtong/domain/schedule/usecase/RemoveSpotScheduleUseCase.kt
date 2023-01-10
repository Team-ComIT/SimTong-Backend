package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.exception.ScheduleExceptions
import team.comit.simtong.domain.schedule.model.value.Scope
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.value.Authority
import team.comit.simtong.global.annotation.UseCase
import java.util.UUID

/**
 *
 * 지점 일정 삭제를 담당하는 RemoveSpotScheduleUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/11/22
 * @version 1.2.5
 **/
@UseCase
class RemoveSpotScheduleUseCase(
    private val queryUserPort: ScheduleQueryUserPort,
    private val querySchedulePort: QuerySchedulePort,
    private val commandSchedulePort: CommandSchedulePort,
    private val securityPort: ScheduleSecurityPort
) {

    fun execute(scheduleId: UUID) {
        val currentUserId = securityPort.getCurrentUserId()

        val user = queryUserPort.queryUserById(currentUserId)
            ?: throw UserExceptions.NotFound()

        val schedule = querySchedulePort.queryScheduleById(scheduleId)
            ?: throw ScheduleExceptions.NotFound()

        schedule.checkScope(Scope.ENTIRE)

        if (!schedule.isSameSpot(user.spotId) && user.authority != Authority.ROLE_SUPER) {
            throw UserExceptions.NotEnoughPermission("같은 지점 관리자이거나 최고 관리자이어야 합니다.")
        }

        commandSchedulePort.delete(schedule)
    }
}