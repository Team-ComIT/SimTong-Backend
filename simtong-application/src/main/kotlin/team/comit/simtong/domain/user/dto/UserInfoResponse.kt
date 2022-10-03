package team.comit.simtong.domain.user.dto

/**
 *
 * 사용자의 정보를 전송하는 UserInfoResponse
 *
 * @author Chokyunghyeon
 * @date 2022/09/27
 * @version 1.0.0
 **/
data class UserInfoResponse (
    val name: String,

    val email: String,

    val nickName: String,

    val spot: String,

    val profileImagePath: String
)