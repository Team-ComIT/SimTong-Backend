package team.comit.simtong.domain.user.dto.request

import java.util.UUID

/**
 *
 * 지점 변경 요청 정보를 전달하는 ChangeSpotData
 *
 * @author Chokyunghyeon
 * @date 2023/01/12
 * @version 1.2.5
 **/
data class ChangeSpotData(
    val spotId: UUID
)