package team.comit.simtong.domain.schedule.dto.request

import java.time.LocalDate

/**
 *
 * 지점 일정 변경 요청 정보를 전달하는 ChangeSpotScheduleData
 *
 * @author Chokyunghyeon
 * @date 2022/11/22
 * @version 1.2.5
 **/
data class ChangeSpotScheduleData(
    val title: String,

    val startAt: LocalDate,

    val endAt: LocalDate
)