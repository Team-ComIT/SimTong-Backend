package team.comit.simtong.domain.schedule.vo

import java.time.LocalDate
import java.util.UUID

/**
 *
 * Schedule과 Schedule의 Spot 정보를 가지는 SpotSchedule
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
open class SpotSchedule(
    val id: UUID,

    val title: String,

    val startAt: LocalDate,

    val endAt: LocalDate,

    val spotId: UUID,

    val spotName: String
)