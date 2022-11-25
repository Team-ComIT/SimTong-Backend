package team.comit.simtong.domain.user.dto.request

import java.util.UUID
import javax.validation.constraints.NotNull

/**
 *
 * 지점 변경을 요청하는 ChangeSpotWebRequest
 *
 * @author kimbeomjin
 * @date 2022/10/15
 * @version 1.0.0
 **/
data class ChangeSpotWebRequest(
    @field:NotNull
    val spotId: UUID
)
