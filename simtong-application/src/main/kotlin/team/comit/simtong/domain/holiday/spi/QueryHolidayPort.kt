package team.comit.simtong.domain.holiday.spi

import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayStatus
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.vo.EmployeeHoliday
import java.time.LocalDate
import java.util.UUID

/**
 *
 * Holiday Domain에 관한 Query를 요청하는 QueryHolidayPort
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/03
 * @version 1.0.0
 **/
interface QueryHolidayPort {

    fun countHolidayByYearAndUserIdAndType(year: Int, userId: UUID, type: HolidayType): Long

    fun countHolidayByWeekAndUserIdAndType(date: LocalDate, userId: UUID, type: HolidayType): Long

    fun queryHolidayByDateAndUserId(date: LocalDate, userId: UUID) : Holiday?

    fun queryHolidaysByPeriodAndUserIdAndStatus(startAt: LocalDate, endAt: LocalDate, userId: UUID, status: HolidayStatus) : List<Holiday>

    fun queryHolidaysByYearAndMonthAndSpotIdAndType(year: Int, month: Int, spotId: UUID, type: HolidayType) : List<Holiday>

    fun queryHolidaysByYearAndMonthAndTeamId(year: Int, month: Int, type: HolidayType?, spotId: UUID, teamId: UUID?) : List<EmployeeHoliday>

    fun existsHolidayByDateAndUserIdAndType(date: LocalDate, userId: UUID, type: HolidayType) : Boolean

}