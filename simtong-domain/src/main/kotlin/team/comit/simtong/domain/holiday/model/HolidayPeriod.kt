package team.comit.simtong.domain.holiday.model

import team.comit.simtong.global.annotation.Aggregate
import java.time.LocalDate
import java.util.UUID

/**
 *
 * 휴무일 작성 기간의 Root Aggregate를 담당하는 HolidayPeriod
 *
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
@Aggregate
data class HolidayPeriod(
    val year: Int,

    val month: Int,

    val spotId: UUID,

    val startAt: LocalDate,

    val endAt: LocalDate
)