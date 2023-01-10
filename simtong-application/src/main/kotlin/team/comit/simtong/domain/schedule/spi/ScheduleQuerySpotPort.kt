package team.comit.simtong.domain.schedule.spi

import team.comit.simtong.domain.spot.model.Spot
import java.util.UUID

/**
 *
 * Schedule Domain에서 Spot에 관한 Query를 요청하는 ScheduleQuerySpotPort
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
interface ScheduleQuerySpotPort {

    fun querySpotById(id: UUID) : Spot?

}