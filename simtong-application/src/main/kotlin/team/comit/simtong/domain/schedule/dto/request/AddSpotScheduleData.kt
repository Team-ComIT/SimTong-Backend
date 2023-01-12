package team.comit.simtong.domain.schedule.dto.request

import java.time.LocalDate

/**
 *
 * 지점 일정 추가 요청 정보를 전달하는 AddSpotScheduleData
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.2.5
 **/
data class AddSpotScheduleData(
    val title: String,

    val startAt: LocalDate,

    val endAt: LocalDate
)