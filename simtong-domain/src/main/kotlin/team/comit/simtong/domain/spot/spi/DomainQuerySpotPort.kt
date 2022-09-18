package team.comit.simtong.domain.spot.spi

import team.comit.simtong.domain.spot.model.Spot

/**
 *
 * Domain에서 Spot에 관한 Query를 요청하는 DomainQuerySpotPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
interface DomainQuerySpotPort {

    fun querySpotByName(name: String): Spot?

}