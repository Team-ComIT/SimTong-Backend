package team.comit.simtong.domain.email.dto

import org.hibernate.validator.constraints.Length
import team.comit.simtong.domain.auth.dto.request.CheckAuthCodeData
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * 이메일 인증 코드 확인을 요청하는 CheckAuthCodeRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.2.5
 **/
data class CheckAuthCodeRequest(
    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    @field:Length(min = 6, max = 6)
    val code: String
) {

    fun toData() = CheckAuthCodeData(
        email = email,
        code = code
    )
}