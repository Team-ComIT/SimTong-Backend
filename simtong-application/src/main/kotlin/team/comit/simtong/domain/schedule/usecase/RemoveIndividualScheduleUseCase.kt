package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.exception.ScheduleNotFoundException
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.NotEnoughPermissionException
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.global.annotation.UseCase
import java.util.UUID

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
            ?: throw UserNotFoundException.EXCEPTION

        val schedule = querySchedulePort.queryScheduleById(scheduleId)
            ?: throw ScheduleNotFoundException.EXCEPTION

        if (user.id != schedule.userId || Scope.INDIVIDUAL != schedule.scope) {
            throw NotEnoughPermissionException.EXCEPTION
        }

        commandSchedulePort.delete(schedule)
    }
}