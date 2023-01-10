package team.comit.simtong.domain.user.dto.request

import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.model.value.EmployeeNumber
import team.comit.simtong.domain.user.model.value.NickName
import team.comit.simtong.domain.user.model.value.Password
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 *
 * 회원 가입을 요청하는 SignUpWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.2.5
 **/
data class SignUpWebRequest(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    val employeeNumber: Int,

    @Pattern(regexp = Password.PATTERN)
    val password: String,

    @Pattern(regexp = NickName.PATTERN)
    val nickname: String,

    val profileImagePath: String?,

    @field:NotBlank
    val deviceToken: String
)
