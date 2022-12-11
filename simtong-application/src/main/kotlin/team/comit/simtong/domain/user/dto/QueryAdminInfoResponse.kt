package team.comit.simtong.domain.user.dto

import java.util.UUID

/**
 *
 * 관리자의 정보를 전송하는 QueryAdminInfoResponse
 *
 * @author Chokyunghyeon
 * @date 2022/12/11
 * @version 1.0.0
 **/
data class QueryAdminInfoResponse(
    val name: String,

    val email: String,

    val nickname: String,

    val spot: SpotResponse,

    val profileImagePath: String
) {
    data class SpotResponse(
        val id: UUID,
        val name: String
    )
}