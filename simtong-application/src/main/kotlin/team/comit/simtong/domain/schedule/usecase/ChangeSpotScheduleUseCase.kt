package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.request.ChangeSpotScheduleData
import team.comit.simtong.domain.schedule.exception.ScheduleExceptions
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.global.annotation.UseCase
import java.util.UUID

/**
 *
 * 지점 일정 변경 기능을 담당하는 ChangeSpotScheduleUseCase
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/11/22
 * @version 1.2.5
 **/
@UseCase
class ChangeSpotScheduleUseCase(
    private val queryUserPort: ScheduleQueryUserPort,
    private val querySchedulePort: QuerySchedulePort,
    private val commandSchedulePort: CommandSchedulePort,
    private val securityPort: ScheduleSecurityPort
) {

    fun execute(request: ChangeSpotScheduleData, scheduleId: UUID) {
        val currentUserId = securityPort.getCurrentUserId()

        val user = queryUserPort.queryUserById(currentUserId)
            ?: throw UserExceptions.NotFound()

        val schedule = querySchedulePort.queryScheduleById(scheduleId)
            ?: throw ScheduleExceptions.NotFound()

        if (!schedule.isSameSpot(user.spotId) && user.authority != Authority.ROLE_SUPER) {
            throw UserExceptions.NotEnoughPermission("같은 지점 관리자이거나 최고 관리자이어야 합니다.")
        }

        commandSchedulePort.save(
            schedule.changeEntireSchedule(
                title = request.title,
                startAt = request.startAt,
                endAt = request.endAt
            )
        )
    }
}