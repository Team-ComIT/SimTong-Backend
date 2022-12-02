package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.QueryEntireSpotScheduleResponse
import team.comit.simtong.domain.schedule.dto.SpotScheduleResponse
import team.comit.simtong.domain.schedule.model.Scope
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

    fun execute(date: LocalDate): QueryEntireSpotScheduleResponse {
        val list = querySchedulePort.querySchedulesByMonthAndScope(date, Scope.ENTIRE)

        val response = list.map {
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
        }

        return QueryEntireSpotScheduleResponse(response)
    }

}