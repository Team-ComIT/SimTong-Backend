package team.comit.simtong.domain.spot.spi

import team.comit.simtong.domain.spot.model.Spot

/**
 *
 * 지점에 관한 Query를 요청하는 QuerySpotPort
 *
 * @author Chokyunghyeon
 * @date 2022/10/18
 * @version 1.0.0
 **/
interface QuerySpotPort {

    fun queryAllSpot(): List<Spot>

}