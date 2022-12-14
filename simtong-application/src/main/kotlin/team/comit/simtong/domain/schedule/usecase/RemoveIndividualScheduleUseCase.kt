package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.exception.ScheduleExceptions
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.global.annotation.UseCase
import java.util.UUID

/**
 *
 * 개인 일정 삭제를 담당하는 RemoveIndividualScheduleUseCase
 *
 * @author kimbeomjin
 * @date 2022/12/03
 * @version 1.0.0
 **/
@UseCase
class RemoveIndividualScheduleUseCase(
    private val querySchedulePort: QuerySchedulePort,
    private val commandSchedulePort: CommandSchedulePort,
    private val queryUserPort: ScheduleQueryUserPort,
    private val securityPort: ScheduleSecurityPort
) {

    fun execute(scheduleId: UUID) {
        val currentUserId = securityPort.getCurrentUserId()

        val user = queryUserPort.queryUserById(currentUserId)
            ?: throw UserExceptions.NotFound()

        val schedule = querySchedulePort.queryScheduleById(scheduleId)
            ?: throw ScheduleExceptions.NotFound()

        if (user.id != schedule.userId) {
            throw ScheduleExceptions.NotScheduleOwner()
        }

        if (Scope.INDIVIDUAL != schedule.scope) {
            throw ScheduleExceptions.DifferentScope("개인 일정이 아닙니다.")
        }

        commandSchedulePort.delete(schedule)
    }
}