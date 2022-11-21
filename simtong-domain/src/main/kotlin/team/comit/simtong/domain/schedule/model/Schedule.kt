package team.comit.simtong.domain.schedule.model

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

/**
 *
 * 일정 Aggregate의 Root를 담당하는 Schedule
 *
 * @author Chokyunghyeon
 * @date 2022/11/21
 * @version 1.0.0
 **/
data class Schedule(
    val id: UUID = UUID(0, 0),

    val userId: UUID,

    val spotId: UUID,

    val title: String,

    val scope: Scope,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val alarmTime: LocalTime = DEFAULT_ALARM_TIME
) {

    companion object {

        /**
         * Default AM 8:30
         */
        val DEFAULT_ALARM_TIME: LocalTime = LocalTime.of(8,30)
    }

}