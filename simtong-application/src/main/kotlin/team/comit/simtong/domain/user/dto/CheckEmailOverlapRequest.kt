package team.comit.simtong.domain.user.dto

/**
 *
 * 이메일 중복 검사를 요청하는 CheckEmailOverlapRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/14
 * @version 1.0.0
 **/
data class CheckEmailOverlapRequest(
    val email: String
)