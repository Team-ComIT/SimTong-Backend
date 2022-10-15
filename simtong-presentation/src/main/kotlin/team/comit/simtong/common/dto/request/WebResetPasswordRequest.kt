package team.comit.simtong.common.dto.request

import org.hibernate.validator.constraints.Length
import team.comit.simtong.RegexUtil
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 *
 * 비밀번호 초기화를 요청하는 WebResetPasswordRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
data class WebResetPasswordRequest(
    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotNull
    @field:Min(1200000000)
    @field:Max(1299999999)
    val employeeNumber: Int,

    @field:NotBlank
    @field:Pattern(regexp = RegexUtil.SECRET_PATTERN)
    @field:Length(min = 8, max = 20)
    val newPassword: String
)