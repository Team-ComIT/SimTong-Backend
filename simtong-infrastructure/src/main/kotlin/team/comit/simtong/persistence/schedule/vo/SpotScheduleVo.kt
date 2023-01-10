package team.comit.simtong.persistence.schedule.vo

import com.querydsl.core.annotations.QueryProjection
import team.comit.simtong.domain.schedule.spi.vo.SpotSchedule
import java.time.LocalDate
import java.util.UUID

/**
 *
 * Schedule과 Schedule의 Spot 정보를 가져오는 SpotScheduleVo
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
class SpotScheduleVo @QueryProjection constructor(
    id: UUID,
    title: String,
    startAt: LocalDate,
    endAt: LocalDate,
    spotId: UUID,
    spotName: String
) : SpotSchedule(
    id = id,
    title = title,
    startAt = startAt,
    endAt = endAt,
    spotId = spotId,
    spotName = spotName
)
