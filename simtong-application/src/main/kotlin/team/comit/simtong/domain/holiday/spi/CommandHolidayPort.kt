package team.comit.simtong.domain.holiday.spi

import team.comit.simtong.domain.holiday.model.Holiday

/**
 *
 * Holiday Domain에 관한 명령을 하는 CommandHolidayPort
 *
 * @author Chokyunghyeon
 * @date 2022/12/03
 * @version 1.0.0
 **/
interface CommandHolidayPort {

    fun save(holiday: Holiday): Holiday
}