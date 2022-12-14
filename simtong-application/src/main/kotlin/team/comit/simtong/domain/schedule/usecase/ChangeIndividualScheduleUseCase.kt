package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.ChangeIndividualScheduleRequest
import team.comit.simtong.domain.schedule.exception.ScheduleExceptions
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 개인 일정 정보 변경 요청을 담당하는 ChangeIndividualScheduleUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/11/27
 * @version 1.0.0
 **/
@UseCase
class ChangeIndividualScheduleUseCase(
    private val queryUserPort: ScheduleQueryUserPort,
    private val querySchedulePort: QuerySchedulePort,
    private val commandSchedulePort: CommandSchedulePort,
    private val securityPort: ScheduleSecurityPort
) {

    fun execute(request: ChangeIndividualScheduleRequest) {
        val currentUserId = securityPort.getCurrentUserId()
        val (scheduleId, title, startAt, endAt, alarm) = request

        val schedule = querySchedulePort.queryScheduleById(scheduleId)
            ?: throw ScheduleExceptions.NotFound()

        val user = queryUserPort.queryUserById(currentUserId)
            ?: throw UserExceptions.NotFound()

        if (user.id != schedule.userId) {
            throw ScheduleExceptions.NotScheduleOwner()
        }

        commandSchedulePort.save(
            schedule.copy(
                title = title,
                startAt = startAt,
                endAt = endAt,
                alarmTime = alarm
            )
        )
    }

}