package team.comit.simtong.domain.holiday.dto.request

import java.time.LocalDate

/**
 *
 * 연차 지정을 요청하는 AppointAnnualWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/19
 * @version 1.0.0
 **/
data class AppointAnnualWebRequest(
    val date: LocalDate
)