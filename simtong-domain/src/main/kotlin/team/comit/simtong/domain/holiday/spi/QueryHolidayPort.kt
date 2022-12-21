package team.comit.simtong.domain.holiday.spi

import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayStatus
import team.comit.simtong.domain.holiday.model.HolidayType
import java.time.LocalDate
import java.util.UUID

/**
 *
 * Holiday Domain에 관한 Query를 요청하는 QueryHolidayPort
 *
 * @author Chokyunghyeon
 * @date 2022/12/03
 * @version 1.0.0
 **/
interface QueryHolidayPort {

    fun countHolidayByYearAndUserIdAndType(year: Int, userId: UUID, type: HolidayType): Long

    fun countHolidayByWeekAndUserIdAndType(date: LocalDate, userId: UUID, type: HolidayType): Long

    fun queryHolidayByDateAndUserId(date: LocalDate, userId: UUID) : Holiday?

    fun queryHolidaysByPeriodAndUserIdAndStatus(startAt: LocalDate, endAt: LocalDate, userId: UUID, status: HolidayStatus) : List<Holiday>

    fun existsHolidayByDateAndUserIdAndType(date: LocalDate, userId: UUID, type: HolidayType) : Boolean

}