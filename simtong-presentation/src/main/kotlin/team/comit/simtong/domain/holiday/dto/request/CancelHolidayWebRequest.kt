package team.comit.simtong.domain.holiday.dto.request

import java.time.LocalDate

/**
 *
 * 근무일 지정을 요청하는 CancelHolidayWebRequest
 *
 * @author kimbeomjin
 * @date 2022/12/20
 * @version 1.0.0
 **/
data class CancelHolidayWebRequest(
    val date: LocalDate
)
