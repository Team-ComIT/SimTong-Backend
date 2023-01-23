package team.comit.simtong.domain.user.dto

import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.dto.request.SignUpData
import team.comit.simtong.domain.user.model.EmployeeNumber
import team.comit.simtong.domain.user.model.NickName
import team.comit.simtong.domain.user.model.Password
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 *
 * 회원 가입을 요청하는 SignUpRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/04
 * @version 1.2.5
 **/
data class SignUpRequest(

    @field:NotBlank
    private val name: String?,

    @field:NotEmpty
    @field:Email
    private val email: String?,

    @field:NotNull
    @field:Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    private val employeeNumber: Int?,

    @field:NotNull
    @field:Pattern(regexp = Password.PATTERN)
    private val password: String?,

    @field:NotNull
    @field:Pattern(regexp = NickName.PATTERN)
    private val nickname: String?,

    private val profileImagePath: String?,

    @field:NotBlank
    private val deviceToken: String?
) {

    fun toData() = SignUpData(
        name = name!!,
        email = email!!,
        employeeNumber = employeeNumber!!,
        password = password!!,
        nickname = nickname!!,
        profileImagePath = profileImagePath,
        deviceToken = deviceToken!!
    )
}