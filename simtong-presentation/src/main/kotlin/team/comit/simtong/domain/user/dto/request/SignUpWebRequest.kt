package team.comit.simtong.domain.user.dto.request

import team.comit.simtong.global.RegexUtil
import javax.validation.constraints.*

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
    @field:Min(1200000000)
    @field:Max(1299999999)
    val employeeNumber: Int,

    @field:Pattern(regexp = RegexUtil.SECRET_PATTERN)
    val password: String,

    @field:Pattern(regexp = RegexUtil.NICKNAME_PATTERN)
    val nickname: String?,

    val profileImagePath: String?
)
