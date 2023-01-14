package team.comit.simtong.domain.admin.dto.request

import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.model.EmployeeNumber
import team.comit.simtong.domain.user.model.Password
import javax.validation.constraints.NotBlank

/**
 *
 * 관리자가 로그인을 요청하는 SignInWebRequest
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2023/01/01
 * @version 1.2.3
 **/
data class SignInWebRequest(

    @Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    val employeeNumber: EmployeeNumber,

    @field:NotBlank
    val password: Password
)