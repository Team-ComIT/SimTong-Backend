package team.comit.simtong.domain.user.model

import team.comit.simtong.global.annotation.Aggregate
import java.util.UUID

/**
 *
 * User Aggregate의 디바이스 토큰 관리를 담당하는 DeviceToken
 *
 * @author kimbeomjin
 * @date 2023/01/01
 * @version 1.2.5
 **/
@Aggregate
data class DeviceToken(
    val userId: UUID,

    val token: String
) {

    companion object {
        fun of(userId: UUID, token: String) = DeviceToken(userId, token)
    }
}
