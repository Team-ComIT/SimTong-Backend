package team.comit.simtong.domain.schedule.dto

import java.time.LocalDate
import java.time.LocalTime

/**
 *
 * 개인 일정 추가 요청의 정보를 전달하는 AddScheduleRequest
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
data class AddScheduleRequest(
    val title: String,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val alarm: LocalTime?
)