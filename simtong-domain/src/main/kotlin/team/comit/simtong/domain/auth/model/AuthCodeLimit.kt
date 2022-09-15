package team.comit.simtong.domain.auth.model

import team.comit.simtong.global.annotation.Aggregate

/**
 *
 * AuthCodeLimit Aggregate Root를 담당하는 AuthCodeLimit
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/11
 * @version 1.0.0
 **/
@Aggregate
class AuthCodeLimit(
    val key: String,

    val expirationTime: Int,

    val attemptCount: Short,

    val isVerified: Boolean
)