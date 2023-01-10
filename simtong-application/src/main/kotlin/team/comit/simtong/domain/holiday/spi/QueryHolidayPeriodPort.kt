package team.comit.simtong.domain.holiday.spi

import team.comit.simtong.domain.holiday.model.HolidayPeriod
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 휴무일 작성 기간에 관한 Query를 요청하는 QueryHolidayPeriodPort
 *
 * @author Chokyunghyeon
 * @date 2022/12/21
 * @version 1.0.0
 **/
interface QueryHolidayPeriodPort {

    fun queryHolidayPeriodByYearAndMonthAndSpotId(year: Int, month: Int, spotId: UUID) : HolidayPeriod?

    fun existsHolidayPeriodByWithinPeriodAndSpotId(date: LocalDate, spotId: UUID) : Boolean

}