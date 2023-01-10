package team.comit.simtong.domain.holiday.model

import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.global.DomainProperties.getProperty
import team.comit.simtong.global.DomainPropertiesPrefix
import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 휴무일의 Root Aggregate를 담당하는 Holiday
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/12/02
 * @version 1.2.5
 **/
@Aggregate
data class Holiday(
    val date: LocalDate,

    val employeeId: UUID,

    val type: HolidayType,

    val spotId: UUID,

    val status: HolidayStatus

) {
    companion object {

        val WEEK_HOLIDAY_LIMIT: Long = getProperty(DomainPropertiesPrefix.WEEK_HOLIDAY_LIMIT).toLong()

        val ANNUAL_LEAVE_LIMIT: Long = getProperty(DomainPropertiesPrefix.ANNUAL_LEAVE_LIMIT).toLong()

        fun createHoliday(
            date: LocalDate,
            userId: UUID,
            type: HolidayType = HolidayType.HOLIDAY,
            spotId: UUID,
            status: HolidayStatus = HolidayStatus.WRITTEN
        ) = Holiday(date, userId, type, spotId, status)

        fun createAnnual(
            date: LocalDate,
            userId: UUID,
            type: HolidayType = HolidayType.ANNUAL,
            spotId: UUID,
            status: HolidayStatus = HolidayStatus.COMPLETED
        ) = Holiday(date, userId, type, spotId, status)

        fun calculateRemainedAnnualCount(annualCount: Long) = ANNUAL_LEAVE_LIMIT - annualCount

        fun checkNotExceededHolidayLimit(holidayCount: Long) {
            if (holidayCount >= WEEK_HOLIDAY_LIMIT) {
                throw HolidayExceptions.WeekHolidayLimitExcess()
            }
        }

        fun checkNotExceededAnnualLimit(annualCount: Long) {
            if (annualCount >= ANNUAL_LEAVE_LIMIT) {
                throw HolidayExceptions.AnnualLeaveLimitExcess()
            }
        }
    }

    fun complete() = this.copy(status = HolidayStatus.COMPLETED)

    fun isSameSpot(spotId: UUID) = this.spotId == spotId

    fun isCompleted() = this.status == HolidayStatus.COMPLETED

    fun change(holidayCount: Long, to: LocalDate): Holiday {
        checkNotExceededHolidayLimit(holidayCount)

        return this.copy(date = to)
    }
}