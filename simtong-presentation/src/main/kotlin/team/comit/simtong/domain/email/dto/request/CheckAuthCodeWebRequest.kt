package team.comit.simtong.domain.email.dto.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * 이메일 인증 코드 확인을 요청하는 CheckAuthCodeWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/24
 * @version 1.0.0
 **/
data class CheckAuthCodeWebRequest(
    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    @field:Length(min = 6, max = 6)
    val code: String
)