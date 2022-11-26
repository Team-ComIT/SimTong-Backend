package team.comit.simtong.domain.schedule.usecase

import team.comit.simtong.domain.schedule.dto.EntireSpotScheduleResponse
import team.comit.simtong.domain.schedule.dto.SpotScheduleResponse
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.time.LocalDate

/**
 *
 * 모든 지점 일정 조회 요청을 담당하는 QuerySpotScheduleUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class EntireSpotScheduleUseCase(
    private val querySchedulePort: QuerySchedulePort
) {

    fun execute(date: LocalDate): EntireSpotScheduleResponse {
        val list = querySchedulePort.querySchedulesByDateContains(date)

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

        return EntireSpotScheduleResponse(response)
    }

}