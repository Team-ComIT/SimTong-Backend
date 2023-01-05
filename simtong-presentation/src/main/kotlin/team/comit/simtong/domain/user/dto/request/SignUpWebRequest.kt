package team.comit.simtong.domain.user.dto.request

import org.hibernate.validator.constraints.Range
import team.comit.simtong.global.value.EmployeeNumber
import team.comit.simtong.global.value.NickName
import team.comit.simtong.global.value.Password
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
 * @version 1.2.3
 **/
data class SignUpWebRequest(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotNull
    @field:Range(
        min = EmployeeNumber.MIN,
        max = EmployeeNumber.MAX
    )
    val employeeNumber: EmployeeNumber,

    @field:Pattern(regexp = Password.PATTERN)
    val password: Password,

    @field:Pattern(regexp = NickName.PATTERN)
    val nickname: NickName,

    val profileImagePath: String?,

    @field:NotBlank
    val deviceToken: String
)
