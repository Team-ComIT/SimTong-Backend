package team.comit.simtong.domain.auth.usecase.dto

import java.util.Date

/**
 *
 * Jwt 토큰을 전송하는 TokenResponse
 *
 * @author Chokyunghyeon
 * @date 2022/09/05
 * @version 1.0.0
 **/
data class TokenResponse(
    val access_token: String,

    val access_token_exp: Date,

    val refresh_token: String
)