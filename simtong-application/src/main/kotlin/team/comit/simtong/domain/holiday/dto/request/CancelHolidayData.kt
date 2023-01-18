package team.comit.simtong.domain.holiday.dto.request

import java.time.LocalDate

/**
 *
 * 휴무일 / 연차 취소 요청 정보를 전달하는 CancelHolidayData
 *
 * @author Chokyunghyeon
 * @date 2023/01/13
 * @version 1.2.5
 **/
data class CancelHolidayData(
    val date: LocalDate
)