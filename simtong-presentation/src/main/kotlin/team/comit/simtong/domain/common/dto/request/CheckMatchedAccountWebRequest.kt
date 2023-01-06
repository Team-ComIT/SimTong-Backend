package team.comit.simtong.domain.common.dto.request

import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.value.EmployeeNumber
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * 해당 사원번호와 이메일을 가지는 계정 여부 확인을 요청하는 CheckMatchedAccountWebRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/15
 * @version 1.2.3
 **/
data class CheckMatchedAccountWebRequest(
    @field:Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    val employeeNumber: EmployeeNumber,

    @field:NotBlank
    @field:Email
    val email: String
)