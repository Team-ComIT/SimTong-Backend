package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.AddIndividualScheduleRequest
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 개인 일정 추가 요청을 담당하는 AddIndividualScheduleUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
@UseCase
class AddIndividualScheduleUseCase(
    private val commandSchedulePort: CommandSchedulePort,
    private val queryUserPort: ScheduleQueryUserPort,
    private val securityPort: ScheduleSecurityPort
) {

    fun execute(request: AddIndividualScheduleRequest) {
        val currentUserId = securityPort.getCurrentUserId()
        val (title, startAt, endAt, alarm) = request

        val user = queryUserPort.queryUserById(currentUserId)
            ?: throw UserExceptions.NotFound()

        commandSchedulePort.save(
            Schedule(
                userId = currentUserId,
                spotId = user.spotId,
                title = title,
                scope = Scope.INDIVIDUAL,
                startAt = startAt,
                endAt = endAt,
                alarmTime = alarm ?: Schedule.DEFAULT_ALARM_TIME
            )
        )
    }

}