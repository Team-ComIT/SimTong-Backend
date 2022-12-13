package team.comit.simtong.domain.common.dto.request

import team.comit.simtong.global.RegexUtils
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 *
 * 비밀번호 변경을 요청하는 ChangePasswordWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/14
 * @version 1.0.0
 **/
data class ChangePasswordWebRequest(

    @field:NotBlank
    val password: String,

    @field:Pattern(regexp = RegexUtils.SECRET_PATTERN)
    val newPassword: String
)