package team.comit.simtong.domain.holiday.dto.request

import java.time.LocalDate
import java.util.UUID

/**
 *
 * 직원의 휴무일 변경을 요청하는 ChangeEmployeeHolidayWebRequest
 *
 * @author kimbeomjin
 * @date 2022/12/23
 * @version 1.0.0
 **/
data class ChangeEmployeeHolidayWebRequest(
    val beforeDate: LocalDate,
    val userId: UUID,
    val afterDate: LocalDate
)
