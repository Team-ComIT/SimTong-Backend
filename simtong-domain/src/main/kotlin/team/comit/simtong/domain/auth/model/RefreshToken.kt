package team.comit.simtong.domain.auth.model

import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.global.annotation.Aggregate
import java.util.*

/**
 *
 * RefreshToken Aggregate Root를 담당하는 RefreshToken
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Aggregate
class RefreshToken(
    val token: String,

    val userId: UUID,

    val authority: Authority,

    val expirationTime: Int
)