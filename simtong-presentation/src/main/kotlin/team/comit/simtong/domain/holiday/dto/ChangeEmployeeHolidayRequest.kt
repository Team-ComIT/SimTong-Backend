package team.comit.simtong.domain.holiday.dto

import team.comit.simtong.domain.holiday.dto.request.ChangeEmployeeHolidayData
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 직원의 휴무일 변경을 요청하는 ChangeEmployeeHolidayRequest
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/23
 * @version 1.2.5
 **/
data class ChangeEmployeeHolidayRequest(
    val beforeDate: LocalDate,

    val userId: UUID,

    val afterDate: LocalDate
) {

    fun toData() = ChangeEmployeeHolidayData(
        beforeDate = beforeDate,
        userId = userId,
        afterDate = afterDate
    )
}
