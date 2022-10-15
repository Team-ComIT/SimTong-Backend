package team.comit.simtong.user.dto.request

import java.util.*
import javax.validation.constraints.NotNull

/**
 *
 * 지점 변경을 요청하는 WebChangeSpotRequest
 *
 * @author kimbeomjin
 * @date 2022/10/15
 * @version 1.0.0
 **/
data class WebChangeSpotRequest(
    @field:NotNull
    val spotId: UUID
)
