package team.comit.simtong.domain.spot.model

import team.comit.simtong.global.DomainProperties
import team.comit.simtong.global.DomainPropertiesPrefix
import team.comit.simtong.global.annotation.Aggregate
import java.util.UUID

/**
 *
 * SpotAggregate Root를 담당하는 Spot
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Aggregate
data class Spot(
    val id: UUID = UUID(0, 0),

    val name: String,

    val location: String
) {
    companion object {
        @JvmField
        val HEAD_SHOP = DomainProperties.getProperty(DomainPropertiesPrefix.HEAD_SHOP)
    }
}