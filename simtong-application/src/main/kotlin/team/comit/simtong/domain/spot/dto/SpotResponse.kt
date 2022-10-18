package team.comit.simtong.domain.spot.dto

import java.util.UUID

/**
 *
 * 지점 리스트를 전송하는 SpotResponse
 *
 * @author Chokyunghyeon
 * @date 2022/10/18
 * @version 1.0.0
 **/
data class SpotResponse(
    val spotList: List<SpotElement>
) {
    data class SpotElement (
        val id: UUID,
        val name: String,
        val location: String
    )

}