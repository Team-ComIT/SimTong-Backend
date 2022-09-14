package team.comit.simtong.domain.auth.model

import team.comit.simtong.global.annotation.Aggregate

/**
 *
 * AuthCodePolicy Aggregate Root를 담당하는 AuthCodePolicy
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.0.0
 **/
@Aggregate
class AuthCodePolicy(
    val key: String,

    val expirationTime: Int,

    val attemptCount: Short,

    val isVerified: Boolean
)