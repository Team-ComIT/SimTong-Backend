package team.comit.simtong.domain.common.dto.request

import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.model.value.EmployeeNumber
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 *
 * 사원번호와 이메일로 계정 여부를 요청하는 CheckMatchedAccountWebRequest
 *
 * @author Chokyunghyeon
 * @date 2023/01/10
 * @version 1.2.5
 **/
data class CheckMatchedAccountWebRequest(
    @field:Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    val employeeNumber: Int,

    @field:NotBlank
    @field:Email
    val email: String
)