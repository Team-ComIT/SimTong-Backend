package team.comit.simtong.domain.spot.dto.response

import java.util.UUID

/**
 *
 * 지점 리스트를 전송하는 QuerySpotsResponse
 *
 * @author Chokyunghyeon
 * @date 2022/10/18
 * @version 1.2.5
 **/
data class QuerySpotsResponse(
    val spotList: List<SpotElement>
) {
    data class SpotElement (
        val id: UUID,
        val name: String,
        val location: String
    )
}