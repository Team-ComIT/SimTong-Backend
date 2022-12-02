package team.comit.simtong.domain.schedule.spi

import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.vo.SpotSchedule
import java.time.LocalDate
import java.util.UUID

/**
 *
 * Schedule에 관한 Query를 요청하는 QuerySchedulePort
 *
 * @author Chokyunghyeon
 * @date 2022/11/22
 * @version 1.0.0
 **/
interface QuerySchedulePort {

    fun queryScheduleById(id: UUID): Schedule?

    fun querySchedulesByMonthAndScope(date: LocalDate, scope: Scope): List<SpotSchedule>

    fun querySchedulesByMonthAndSpotId(date: LocalDate, spotId: UUID): List<Schedule>

    fun querySchedulesByMonthAndUserId(date: LocalDate, userId: UUID): List<Schedule>

}