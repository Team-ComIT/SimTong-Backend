package team.comit.simtong.domain.schedule.spi

import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.model.value.Scope
import team.comit.simtong.domain.schedule.spi.vo.SpotSchedule
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

    fun querySpotSchedulesByPeriodAndScope(startAt: LocalDate, endAt: LocalDate, scope: Scope): List<SpotSchedule>

    fun querySchedulesByPeriodAndSpotIdAndScope(startAt: LocalDate, endAt: LocalDate, spotId: UUID, scope: Scope): List<Schedule>

    fun querySchedulesByPeriodAndUserIdAndScope(startAt: LocalDate, endAt: LocalDate, userId: UUID, scope: Scope): List<Schedule>

}