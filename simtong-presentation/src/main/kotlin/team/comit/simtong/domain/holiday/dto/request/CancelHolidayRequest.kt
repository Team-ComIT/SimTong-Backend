package team.comit.simtong.domain.holiday.dto.request

import java.time.LocalDate

/**
 *
 * 근무일 지정을 요청하는 CancelHolidayRequest
 *
 * @author kimbeomjin
 * @date 2022/12/20
 * @version 1.0.0
 **/
data class CancelHolidayRequest(
    val date: LocalDate
)
