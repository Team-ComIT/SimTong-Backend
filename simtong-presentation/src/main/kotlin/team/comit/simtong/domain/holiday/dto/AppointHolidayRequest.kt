package team.comit.simtong.domain.holiday.dto

import team.comit.simtong.domain.holiday.dto.request.AppointHolidayData
import java.time.LocalDate

/**
 *
 * 휴무일 지정을 요청하는 AppointHolidayRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/03
 * @version 1.2.5
 **/
data class AppointHolidayRequest(
    val date: LocalDate
) {

    fun toData() = AppointHolidayData(
        date = date
    )
}