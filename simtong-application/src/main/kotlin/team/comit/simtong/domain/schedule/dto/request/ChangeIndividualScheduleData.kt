package team.comit.simtong.domain.schedule.dto.request

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

/**
 *
 * 개인 일정 변경 요청 정보를 전달하는 ChangeIndividualScheduleData
 *
 * @author Chokyunghyeon
 * @date 2022/11/27
 * @version 1.2.5
 **/
data class ChangeIndividualScheduleData(

    val scheduleId: UUID,

    val title: String,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val alarm: LocalTime
)