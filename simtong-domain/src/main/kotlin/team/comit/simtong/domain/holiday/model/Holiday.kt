package team.comit.simtong.domain.holiday.model

import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 휴무일의 Root Aggregate를 담당하는 Holiday
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
 **/
@Aggregate
data class Holiday(
    val date: LocalDate,

    val userId: UUID,

    val type: HolidayType,

    val spotId: UUID

) {
    companion object {

        const val WEEK_HOLIDAY_LIMIT: Long = 2

    }
}