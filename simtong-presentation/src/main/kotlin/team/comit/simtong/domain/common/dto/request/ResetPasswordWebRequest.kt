package team.comit.simtong.domain.common.dto.request

import org.hibernate.validator.constraints.Range
import team.comit.simtong.global.value.EmployeeNumber
import team.comit.simtong.global.value.Password
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 *
 * 비밀번호 초기화를 요청하는 ResetPasswordWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
data class ResetPasswordWebRequest(
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
    val newPassword: Password
)