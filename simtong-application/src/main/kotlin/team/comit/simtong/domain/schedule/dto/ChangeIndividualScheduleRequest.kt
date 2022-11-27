package team.comit.simtong.domain.schedule.dto

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

/**
 *
 * 개인 일정 변경 요청 정보를 전달하는 ChangeIndividualScheduleRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/27
 * @version 1.0.0
 **/
data class ChangeIndividualScheduleRequest(

    val scheduleId: UUID,

    val title: String,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val alarm: LocalTime
)