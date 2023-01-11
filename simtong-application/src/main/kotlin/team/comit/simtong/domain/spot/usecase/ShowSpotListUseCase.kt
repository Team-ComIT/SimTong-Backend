package team.comit.simtong.domain.spot.usecase

import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.domain.spot.spi.QuerySpotPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 지점 리스트를 담당하는 ShowSpotListUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/10/18
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class ShowSpotListUseCase(
    private val querySpotPort: QuerySpotPort
) {

    fun execute(): List<Spot> {
        return querySpotPort.queryAllSpot()
    }
}