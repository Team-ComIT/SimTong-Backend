package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.spot.model.Spot

/**
 *
 * User에서 Spot에 관한 Query를 요청하는 UserQuerySpotPort
 *
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.0.0
 **/
interface UserQuerySpotPort {

    fun querySpotByName(name: String): Spot?

}