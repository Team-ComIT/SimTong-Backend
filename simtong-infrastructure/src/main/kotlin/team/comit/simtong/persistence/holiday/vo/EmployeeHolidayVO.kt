package team.comit.simtong.persistence.holiday.vo

import com.querydsl.core.annotations.QueryProjection
import team.comit.simtong.domain.holiday.vo.EmployeeHoliday
import team.comit.simtong.domain.holiday.model.HolidayType
import java.time.LocalDate
import java.util.UUID

/**
 *
 * Holiday와 User 정보를 가져오는 EmployeeHolidayVO
 *
 * @author kimbeomjin
 * @date 2022/12/22
 * @version 1.0.0
 **/
class EmployeeHolidayVO @QueryProjection constructor(
    date: LocalDate,
    type: HolidayType,
    userId: UUID,
    userName: String,
    teamName: String,
    spotName: String
) : EmployeeHoliday(
    date = date,
    type = type,
    userId = userId,
    userName = userName,
    teamName = teamName,
    spotName = spotName
)
