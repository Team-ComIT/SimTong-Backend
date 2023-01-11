package team.comit.simtong.domain.holiday.dto.request

import java.time.LocalDate

/**
 *
 * 휴무표 작성 기간 설정 요청 정보를 전달하는 AppointHolidayPeriodRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/22
 * @version 1.0.0
 **/
data class AppointHolidayPeriodRequest(
    val year: Int,

    val month: Int,

    val startAt: LocalDate,

    val endAt: LocalDate
)