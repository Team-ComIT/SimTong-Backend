package team.comit.simtong.domain.email.dto

import team.comit.simtong.domain.auth.dto.request.SendAuthCodeData
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

/**
 *
 * 이메일 인증 코드 전송을 요청하는 SendEmailRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.2.5
 **/
data class SendAuthCodeRequest(

    @field:NotEmpty
    @field:Email
    private val email: String?
) {

    fun toData() = SendAuthCodeData(
        email = email!!
    )
}