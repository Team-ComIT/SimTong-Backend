package team.comit.simtong.domain.schedule.dto

import team.comit.simtong.domain.schedule.model.Scope
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 개인 일정과 소속 지점의 일정 조회 정보를 다루는 QueryIndividualSpotScheduleResponse
 *
 * @author kimbeomjin
 * @date 2022/12/02
 * @version 1.0.0
 **/
data class QueryIndividualSpotScheduleResponse(
    val schedules: List<ScheduleResponse>
)

/**
 *
 * 일정 조회 정보를 다루는 ScheduleResponse
 *
 * @author kimbeomjin
 * @date 2022/12/02
 * @version 1.0.0
 **/
data class ScheduleResponse(
    val id: UUID,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val title: String,

    val scope: Scope
)