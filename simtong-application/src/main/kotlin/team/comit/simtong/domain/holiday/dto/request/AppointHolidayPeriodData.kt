package team.comit.simtong.domain.holiday.dto.request

import java.time.LocalDate

/**
 *
 * 휴무표 작성 기간 설정 요청 정보를 전달하는 AppointHolidayPeriodData
 *
 * @author Chokyunghyeon
 * @date 2022/12/22
 * @version 1.2.5
 **/
data class AppointHolidayPeriodData(
    val year: Int,

    val month: Int,

    val startAt: LocalDate,

    val endAt: LocalDate
)