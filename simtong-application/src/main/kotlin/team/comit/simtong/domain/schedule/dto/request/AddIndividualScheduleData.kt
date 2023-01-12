package team.comit.simtong.domain.schedule.dto.request

import java.time.LocalDate
import java.time.LocalTime

/**
 *
 * 개인 일정 추가 요청의 정보를 전달하는 AddIndividualScheduleData
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.2.5
 **/
data class AddIndividualScheduleData(
    val title: String,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val alarm: LocalTime?
)