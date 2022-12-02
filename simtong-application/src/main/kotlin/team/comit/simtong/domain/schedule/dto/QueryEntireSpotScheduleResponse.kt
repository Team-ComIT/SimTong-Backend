package team.comit.simtong.domain.schedule.dto

import java.time.LocalDate
import java.util.UUID

/**
 *
 * 모든 지점 일정 조회 정보를 전송하는 QueryEntireSpotScheduleResponse
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
data class QueryEntireSpotScheduleResponse(
    val schedules: List<SpotScheduleResponse>
)

/**
 *
 * 지점 일정 정보를 전송하는 SpotScheduleResponse
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
data class SpotScheduleResponse(
    val id: UUID,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val title: String,

    val spot: SpotElement
) {
    data class SpotElement(
        val id: UUID,
        val name: String
    )
}