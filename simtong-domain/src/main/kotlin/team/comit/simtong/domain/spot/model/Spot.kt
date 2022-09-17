package team.comit.simtong.domain.spot.model

import team.comit.simtong.global.annotation.Aggregate
import java.util.*

/**
 *
 * SpotAggregate Root를 담당하는 Spot
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Aggregate
class Spot(
    val id: UUID = UUID(0, 0),

    val name: String,

    val location: String
)