package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.response.QueryEntireSpotScheduleResponse
import team.comit.simtong.domain.schedule.dto.response.SpotScheduleResponse
import team.comit.simtong.domain.schedule.model.value.Scope
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.time.LocalDate

/**
 *
 * 모든 지점 일정 조회 요청을 담당하는 QueryEntireSpotScheduleUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryEntireSpotScheduleUseCase(
    private val querySchedulePort: QuerySchedulePort
) {

    fun execute(startAt: LocalDate, endAt: LocalDate): QueryEntireSpotScheduleResponse {
        val spotSchedules = querySchedulePort.querySpotSchedulesByPeriodAndScope(startAt, endAt, Scope.ENTIRE)

        return spotSchedules.map {
            SpotScheduleResponse(
                id = it.id,
                startAt = it.startAt,
                endAt = it.endAt,
                title = it.title,
                spot = SpotScheduleResponse.SpotElement(
                    id = it.spotId,
                    name = it.spotName
                )
            )
        }.let(::QueryEntireSpotScheduleResponse)
    }
}