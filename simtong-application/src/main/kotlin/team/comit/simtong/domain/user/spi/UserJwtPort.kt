package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.user.model.Authority
import java.util.UUID

/**
 *
 * User Domain에서 Jwt에 관한 요청하는 UserJwtPort
 *
 * @author kimbeomjin
 * @date 2022/09/18
 * @version 1.0.0
 **/
interface UserJwtPort {

    fun receiveToken(userId: UUID, authority: Authority): TokenResponse

}