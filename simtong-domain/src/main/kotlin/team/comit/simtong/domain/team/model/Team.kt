package team.comit.simtong.domain.team.model

import team.comit.simtong.global.annotation.Aggregate
import java.util.UUID

/**
 *
 * TeamAggregate Root를 담당하는 Team
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Aggregate
data class Team(
    val id: UUID = UUID(0, 0),

    val name: String,

    val spotId: UUID
)