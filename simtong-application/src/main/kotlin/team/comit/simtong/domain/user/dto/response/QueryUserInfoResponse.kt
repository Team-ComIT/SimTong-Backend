package team.comit.simtong.domain.user.dto.response

/**
 *
 * 유저의 정보를 전송하는 QueryUserInfoResponse
 *
 * @author Chokyunghyeon
 * @date 2022/09/27
 * @version 1.0.0
 **/
data class QueryUserInfoResponse (
    val name: String,

    val email: String,

    val nickname: String,

    val spot: String,

    val profileImagePath: String
)