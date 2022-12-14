package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.ChangeSpotScheduleRequest
import team.comit.simtong.domain.schedule.exception.ScheduleExceptions
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
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
    private val queryUserPort: ScheduleQueryUserPort,
    private val querySchedulePort: QuerySchedulePort,
    private val commandSchedulePort: CommandSchedulePort,
    private val securityPort: ScheduleSecurityPort
) {

    fun execute(request: ChangeSpotScheduleRequest) {
        val currentUserId = securityPort.getCurrentUserId()

        val user = queryUserPort.queryUserById(currentUserId)
            ?: throw UserExceptions.NotFound()

        val schedule = querySchedulePort.queryScheduleById(request.scheduleId)
            ?: throw ScheduleExceptions.NotFound()

        when {
            Scope.ENTIRE != schedule.scope -> throw ScheduleExceptions.NotScheduleOwner()

            user.spotId != schedule.spotId && Authority.ROLE_SUPER != user.authority ->
                throw UserExceptions.NotEnoughPermission("같은 지점 관리자이거나 최고 관리자이어야 합니다.")
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