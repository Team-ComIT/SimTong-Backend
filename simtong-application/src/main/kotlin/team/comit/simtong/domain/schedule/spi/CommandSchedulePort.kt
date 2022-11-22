package team.comit.simtong.domain.schedule.spi

import team.comit.simtong.domain.schedule.model.Schedule

/**
 *
 * Schedule Domain에 관한 명령을 하는 CommandSchedulePort
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
interface CommandSchedulePort {

    fun save(schedule: Schedule): Schedule

}