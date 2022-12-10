package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.QueryIndividualSpotScheduleResponse
import team.comit.simtong.domain.schedule.dto.ScheduleResponse
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.time.LocalDate

/**
 *
 * 개인 일정과 소속 지점 일정을 조회하는 QueryIndividualSpotScheduleUseCase
 *
 * @author kimbeomjin
 * @date 2022/12/02
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryIndividualSpotScheduleUseCase(
    private val querySchedulePort: QuerySchedulePort,
    private val queryUserPort: ScheduleQueryUserPort,
    private val securityPort: ScheduleSecurityPort
) {

    fun execute(date: LocalDate): QueryIndividualSpotScheduleResponse {
        val user = queryUserPort.queryUserById(securityPort.getCurrentUserId())
            ?: throw UserNotFoundException.EXCEPTION

        val individualSchedules = querySchedulePort.querySchedulesByMonthAndUserIdAndScope(
            date, user.id, Scope.INDIVIDUAL
        )

        val ownSpotSchedules = querySchedulePort.querySchedulesByMonthAndSpotIdAndScope(
            date, user.spotId, Scope.ENTIRE
        )

        val schedules = ownSpotSchedules.union(individualSchedules) // 개인 일정과 소속 지점 일정 합치면서 중복 제거
            .sortedBy { it.startAt } // 시작일 기준 오름차순으로 정렬

        val response = schedules.map {
            ScheduleResponse(
                id = it.id,
                startAt = it.startAt,
                endAt = it.endAt,
                title = it.title,
                scope = it.scope
            )
        }

        return QueryIndividualSpotScheduleResponse(response)
    }
}