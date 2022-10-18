package team.comit.simtong.domain.spot.usecase

import team.comit.simtong.domain.spot.dto.SpotResponse
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

    fun execute(): SpotResponse {
        val result = querySpotPort.querySpotAll().map {
            SpotResponse.SpotElement(
                id = it.id,
                name = it.name,
                location = it.location
            )
        }

        return SpotResponse(result)
    }

}