package team.comit.simtong.common.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * 이메일 중복 여부를 요청하는 WebCheckEmailDuplicationRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/14
 * @version 1.0.0
 **/
data class WebCheckEmailDuplicationRequest(
    @field:NotBlank
    @field:Email
    val email: String
)