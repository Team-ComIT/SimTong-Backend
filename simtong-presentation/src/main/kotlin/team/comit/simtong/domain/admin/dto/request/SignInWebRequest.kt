package team.comit.simtong.domain.admin.dto.request

import team.comit.simtong.global.value.EmployeeNumber
import team.comit.simtong.global.value.Password
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 관리자가 로그인을 요청하는 SignInWebRequest
 *
 * @author kimbeomjin
 * @date 2023/01/01
 * @version 1.2.3
 **/
data class SignInWebRequest(

    @field:NotNull
    val employeeNumber: EmployeeNumber,

    @field:NotBlank
    val password: Password
)