package team.comit.simtong.domain.user.dto

import team.comit.simtong.domain.user.dto.request.ChangeSpotData
import java.util.UUID
import javax.validation.constraints.NotNull

/**
 *
 * 지점 변경을 요청하는 ChangeSpotRequest
 *
 * @author kimbeomjin
 * @date 2022/10/15
 * @version 1.0.0
 **/
data class ChangeSpotRequest(

    @field:NotNull
    val spotId: UUID?
) {

    fun toData() = ChangeSpotData(
        spotId = spotId!!
    )
}