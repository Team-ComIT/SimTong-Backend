package team.comit.simtong.domain.holiday.spi.vo

import team.comit.simtong.domain.holiday.model.value.HolidayType
import java.time.LocalDate
import java.util.UUID

/**
 *
 * Holiday와 User 정보를 가져오는 EmployeeHoliday
 *
 * @author kimbeomjin
 * @date 2022/12/22
 * @version 1.0.0
 **/
open class EmployeeHoliday(
    val date: LocalDate,

    val type: HolidayType,

    val userId: UUID,

    val userName: String,

    val teamName: String,

    val spotName: String
)
