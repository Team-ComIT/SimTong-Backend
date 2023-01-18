package team.comit.simtong.domain.auth.dto.request

/**
 *
 * 인증 코드 전송 요청 정보를 전달하는 SendAuthCodeData
 *
 * @author Chokyunghyeon
 * @date 2023/01/14
 * @version 1.2.5
 **/
data class SendAuthCodeData(
    val email: String
)