package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.spot.model.Spot
import java.util.*

/**
 *
 * User에서 Spot에 관한 Query를 요청하는 UserQuerySpotPort
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
interface UserQuerySpotPort {

    fun querySpotByName(name: String): Spot?

    fun querySpotById(id: UUID): Spot?

    fun existsSpotById(id: UUID): Boolean

}