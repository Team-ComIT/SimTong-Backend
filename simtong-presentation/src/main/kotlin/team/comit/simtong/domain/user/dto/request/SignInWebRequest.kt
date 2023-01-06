package team.comit.simtong.domain.user.dto.request

import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.value.EmployeeNumber
import team.comit.simtong.domain.user.value.Password
import javax.validation.constraints.NotBlank

/**
 *
 * 일반 사용자가 로그인을 요청하는 SignInWebRequest
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.2.3
 **/
data class SignInWebRequest(

    @field:Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    val employeeNumber: EmployeeNumber,

    @field:NotBlank
    val password: Password,

    @field:NotBlank
    val deviceToken: String
)