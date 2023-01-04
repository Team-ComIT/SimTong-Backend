package team.comit.simtong.domain.holiday.spi

import team.comit.simtong.domain.holiday.model.Holiday

/**
 *
 * Holiday Domain에 관한 명령을 하는 CommandHolidayPort
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/03
 * @version 1.2.3
 **/
interface CommandHolidayPort {

    fun save(holiday: Holiday): Holiday

    fun saveAll(holidays: List<Holiday>) : List<Holiday>

    fun delete(holiday: Holiday)

}