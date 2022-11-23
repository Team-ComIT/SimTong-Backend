package team.comit.simtong.domain.schedule.dto

import java.time.LocalDate
import java.util.UUID

/**
 *
 * 지점 일정 변경 요청 정보를 전달하는 ChangeSpotScheduleRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/22
 * @version 1.0.0
 **/
data class ChangeSpotScheduleRequest(
    val scheduleId: UUID,

    val title: String,

    val startAt: LocalDate,

    val endAt: LocalDate
)