package team.comit.simtong.user.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * 이메일 변경을 요청하는 WebChangeEmailRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
data class WebChangeEmailRequest(
    @field:NotBlank
    @field:Email
    val email: String
)