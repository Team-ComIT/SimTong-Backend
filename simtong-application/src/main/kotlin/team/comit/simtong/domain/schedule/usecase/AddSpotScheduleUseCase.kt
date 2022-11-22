package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.AddSpotScheduleRequest
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.NotEnoughPermissionException
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 지점 일정 추가를 담당하는 AddSpotScheduleUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
@UseCase
class AddSpotScheduleUseCase(
    private val scheduleQueryUserPort: ScheduleQueryUserPort,
    private val commandSchedulePort: CommandSchedulePort,
    private val scheduleSecurityPort: ScheduleSecurityPort
) {

    fun execute(request: AddSpotScheduleRequest) {
        val currentUserId = scheduleSecurityPort.getCurrentUserId()
        val (spotId, title, startAt, endAt) = request

        val user = scheduleQueryUserPort.queryUserById(currentUserId)
            ?: throw UserNotFoundException.EXCEPTION

        if (user.authority != Authority.ROLE_SUPER && spotId != user.spotId) {
            throw NotEnoughPermissionException.EXCEPTION
        }

        commandSchedulePort.save(
            Schedule(
                userId = currentUserId,
                spotId = spotId,
                title = title,
                scope = Scope.ENTIRE,
                startAt = startAt,
                endAt = endAt
            )
        )
    }

}