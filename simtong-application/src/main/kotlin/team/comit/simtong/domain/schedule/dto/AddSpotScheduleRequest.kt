package team.comit.simtong.domain.schedule.dto

import java.time.LocalDate
import java.util.UUID

/**
 *
 * 지점 일정 추가 요청 정보를 전달하는 AddSpotScheduleRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
data class AddSpotScheduleRequest(
    val spotId: UUID,

    val title: String,

    val startAt: LocalDate,

    val endAt: LocalDate
)