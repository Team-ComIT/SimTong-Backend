package team.comit.simtong.domain.common.dto.request

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.value.EmployeeNumber
import team.comit.simtong.domain.user.value.Password
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 *
 * 비밀번호 초기화를 요청하는 ResetPasswordWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.2.3
 **/
data class ResetPasswordWebRequest(
    @field:NotBlank
    @field:Email
    val email: String,

    @field:Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    val employeeNumber: EmployeeNumber,

    @field:Length(min = Password.MIN_LENGTH, max = Password.MAX_LENGTH)
    @field:Pattern(regexp = Password.PATTERN)
    val newPassword: Password
)