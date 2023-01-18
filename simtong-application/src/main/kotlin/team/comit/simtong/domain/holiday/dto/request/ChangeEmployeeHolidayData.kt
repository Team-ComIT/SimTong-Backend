package team.comit.simtong.domain.holiday.dto.request

import java.time.LocalDate
import java.util.UUID

/**
 *
 * 직원 휴무일 변경 요청 정보를 전달하는 ChangeEmployeeHolidayData
 *
 * @author Chokyunghyeon
 * @date 2023/01/13
 * @version 1.2.5
 **/
data class ChangeEmployeeHolidayData(
    val beforeDate: LocalDate,

    val userId: UUID,

    val afterDate: LocalDate
)