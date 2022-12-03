package team.comit.simtong.domain.holiday.spi

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

    fun countHolidayByWeekAndUserIdAndType(date: LocalDate, userId: UUID, type: HolidayType): Long

}