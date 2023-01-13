package team.comit.simtong.domain.holiday.dto.response

import java.time.LocalDate

/**
 *
 * 해당 달의 휴무표 작성 기간을 조회한 결과를 전달하는 QueryMonthHolidayPeriodResponse
 *
 * @author Chokyunghyeon
 * @date 2022/12/22
 * @version 1.0.0
 **/
data class QueryMonthHolidayPeriodResponse(
    val startAt: LocalDate,
    val endAt: LocalDate
)