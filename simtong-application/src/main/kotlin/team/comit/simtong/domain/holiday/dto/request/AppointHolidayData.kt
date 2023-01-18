package team.comit.simtong.domain.holiday.dto.request

import java.time.LocalDate

/**
 *
 * 휴무일 지정 요청 정보를 전달하는 AppointHolidayData
 *
 * @author Chokyunghyeon
 * @date 2023/01/13
 * @version 1.2.5
 **/
data class AppointHolidayData(
    val date: LocalDate
)