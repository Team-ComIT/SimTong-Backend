package team.comit.simtong.email.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * 이메일 인증 코드 전송을 요청하는 SendEmailWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
data class SendAuthCodeWebRequest(
    @field:NotBlank
    @field:Email
    val email: String
)