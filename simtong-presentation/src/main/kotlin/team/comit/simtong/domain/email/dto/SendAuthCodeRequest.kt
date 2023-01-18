package team.comit.simtong.domain.email.dto

import team.comit.simtong.domain.auth.dto.request.SendAuthCodeData
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * 이메일 인증 코드 전송을 요청하는 SendEmailRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.2.5
 **/
data class SendAuthCodeRequest(
    @field:NotBlank
    @field:Email
    val email: String
) {

    fun toData() = SendAuthCodeData(
        email = email
    )
}