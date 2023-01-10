package team.comit.simtong.domain.schedule.model

import team.comit.simtong.domain.schedule.exception.ScheduleExceptions
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

/**
 *
 * 일정 Aggregate의 Root를 담당하는 Schedule
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/11/21
 * @version 1.2.5
 **/
data class Schedule(
    val id: UUID,

    val userId: UUID,

    val spotId: UUID,

    val title: String,

    val scope: Scope,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val alarmTime: LocalTime
) {

    companion object {

        /**
         * Default AM 8:30
         */
        val DEFAULT_ALARM_TIME: LocalTime = LocalTime.of(8, 30)

        fun of(
            id: UUID = UUID(0, 0),
            userId: UUID,
            spotId: UUID,
            title: String,
            scope: Scope,
            startAt: LocalDate,
            endAt: LocalDate,
            alarmTime: LocalTime = DEFAULT_ALARM_TIME
        ) = Schedule(
            id = id,
            userId = userId,
            spotId = spotId,
            title = title,
            scope = scope,
            startAt = startAt,
            endAt = endAt,
            alarmTime = alarmTime
        )
    }

    fun isSameSpot(spotId: UUID) = this.spotId == spotId

    fun changeIndividualSchedule(
        title: String,
        startAt: LocalDate,
        endAt: LocalDate,
        alarmTime: LocalTime,
        userId: UUID
    ): Schedule {
        checkScope(Scope.INDIVIDUAL)
        checkOwner(userId)

        return this.copy(
            title = title,
            startAt = startAt,
            endAt = endAt,
            alarmTime = alarmTime
        )
    }

    fun changeEntireSchedule(
        title: String,
        startAt: LocalDate,
        endAt: LocalDate
    ): Schedule {
        checkScope(Scope.ENTIRE)

        return this.copy(
            title = title,
            startAt = startAt,
            endAt = endAt
        )
    }

    fun checkOwner(userId: UUID) {
        if (this.userId == userId) {
            throw ScheduleExceptions.NotScheduleOwner()
        }
    }

    fun checkScope(scope: Scope) {
        when (scope) {
            Scope.INDIVIDUAL -> if (this.scope != Scope.INDIVIDUAL) throw ScheduleExceptions.DifferentScope("개인 일정이 아닙니다.")

            Scope.ENTIRE -> if (this.scope != Scope.ENTIRE) throw ScheduleExceptions.DifferentScope("지점 일정이 아닙니다.")
        }
    }
}