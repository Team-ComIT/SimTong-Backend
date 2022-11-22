package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.ChangeSpotScheduleRequest
import team.comit.simtong.domain.schedule.exception.ScheduleNotFoundException
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.NotEnoughPermissionException
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 지점 일정 변경 기능을 담당하는 ChangeSpotScheduleUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/11/22
 * @version 1.0.0
 **/
@UseCase
class ChangeSpotScheduleUseCase(
    private val scheduleQueryUserPort: ScheduleQueryUserPort,
    private val querySchedulePort: QuerySchedulePort,
    private val commandSchedulePort: CommandSchedulePort,
    private val scheduleSecurityPort: ScheduleSecurityPort
) {

    fun execute(request: ChangeSpotScheduleRequest) {
        val currentUserId = scheduleSecurityPort.getCurrentUserId()

        val user = scheduleQueryUserPort.queryUserById(currentUserId)
            ?: throw UserNotFoundException.EXCEPTION

        val schedule = querySchedulePort.queryScheduleById(request.scheduleId)
            ?: throw ScheduleNotFoundException.EXCEPTION

        if (user.spotId != schedule.spotId && user.authority != Authority.ROLE_SUPER) {
            throw NotEnoughPermissionException.EXCEPTION
        }

        commandSchedulePort.save(
            schedule.copy(
                title = request.title,
                startAt = request.startAt,
                endAt = request.endAt
            )
        )
    }
}