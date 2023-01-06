package team.comit.simtong.domain.user.dto.request

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.value.EmployeeNumber
import team.comit.simtong.domain.user.value.NickName
import team.comit.simtong.domain.user.value.Password
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
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

    @Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    val employeeNumber: EmployeeNumber,

    @Length(min = Password.MIN_LENGTH, max = Password.MAX_LENGTH)
    @Pattern(regexp = Password.PATTERN)
    val password: Password,

    @Length(min = NickName.MIN_LENGTH, max = NickName.MAX_LENGTH)
    @Pattern(regexp = NickName.PATTERN)
    val nickname: NickName,

    val profileImagePath: String?,

    @field:NotBlank
    val deviceToken: String
)
