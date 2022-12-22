package team.comit.simtong.domain.holiday.spi

import team.comit.simtong.domain.holiday.model.HolidayPeriod

/**
 *
 * 휴무표 작성 기간에 관한 명령을 하는 CommandHolidayPeriodPort
 *
 * @author Chokyunghyeon
 * @date 2022/12/22
 * @version 1.0.0
 **/
interface CommandHolidayPeriodPort {

    fun save(holidayPeriod: HolidayPeriod) : HolidayPeriod

}