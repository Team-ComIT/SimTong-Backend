package team.comit.simtong.domain.user.dto.request

import org.hibernate.validator.constraints.Range
import team.comit.simtong.global.RegexUtils
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 *
 * 회원 가입을 요청하는 SignUpWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.0.0
 **/
data class SignUpWebRequest(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotNull
    @field:Range(
        min = 1200000000,
        max = 1299999999
    )
    val employeeNumber: Int,

    @field:Pattern(regexp = RegexUtils.SECRET_PATTERN)
    val password: String,

    @field:Pattern(regexp = RegexUtils.NICKNAME_PATTERN)
    val nickname: String?,

    val profileImagePath: String?,

    @field:NotBlank
    val deviceToken: String
)
