package team.comit.simtong.domain.spot.usecase

import team.comit.simtong.domain.spot.dto.response.QuerySpotsResponse
import team.comit.simtong.domain.spot.spi.QuerySpotPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase

/**
 *
 * 지점 리스트를 담당하는 ShowSpotListUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/10/18
 * @version 1.2.5
 **/
@ReadOnlyUseCase
class ShowSpotListUseCase(
    private val querySpotPort: QuerySpotPort
) {

    fun execute(): QuerySpotsResponse {
        val spots = querySpotPort.queryAllSpot()

        return spots.map {
            QuerySpotsResponse.SpotElement(
                id = it.id,
                name = it.name,
                location = it.location
            )
        }.let(::QuerySpotsResponse)
    }
}