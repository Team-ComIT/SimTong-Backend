package team.comit.simtong.domain.holiday.dto.request

import java.time.LocalDate

/**
 *
 * 연차 지정 요청 정보를 전달하는 AppointAnnualData
 *
 * @author Chokyunghyeon
 * @date 2023/01/13
 * @version 1.2.5
 **/
data class AppointAnnualData(
    val date: LocalDate
)