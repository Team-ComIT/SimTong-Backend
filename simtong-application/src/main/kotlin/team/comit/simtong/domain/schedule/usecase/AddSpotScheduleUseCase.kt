package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.AddSpotScheduleRequest
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.model.value.Scope
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.value.Authority
import team.comit.simtong.global.annotation.UseCase

/**
 *
 * 지점 일정 추가를 담당하는 AddSpotScheduleUseCase
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/11/21
 * @version 1.2.5
 **/
@UseCase
class AddSpotScheduleUseCase(
    private val queryUserPort: ScheduleQueryUserPort,
    private val commandSchedulePort: CommandSchedulePort,
    private val securityPort: ScheduleSecurityPort
) {

    fun execute(request: AddSpotScheduleRequest) {
        val currentUserId = securityPort.getCurrentUserId()
        val (spotId, title, startAt, endAt) = request

        val user = queryUserPort.queryUserById(currentUserId)
            ?: throw UserExceptions.NotFound()

        if (user.spotId != spotId && user.authority != Authority.ROLE_SUPER) {
            throw UserExceptions.NotEnoughPermission("같은 지점 관리자이거나 최고 관리자이어야 합니다.")
        }

        commandSchedulePort.save(
            Schedule.of(
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