package team.comit.simtong.domain.auth.dto.request

/**
 *
 * 인증 코드 확인 요청 정보를 전달하는 CheckAuthCodeData
 *
 * @author Chokyunghyeon
 * @date 2023/01/14
 * @version 1.2.5
 **/
data class CheckAuthCodeData(
    val email: String,

    val code: String
)