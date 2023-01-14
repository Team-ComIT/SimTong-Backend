package team.comit.simtong.domain.admin.dto

import org.hibernate.validator.constraints.Range
import team.comit.simtong.domain.user.dto.request.AdminSignInData
import team.comit.simtong.domain.user.model.EmployeeNumber
import javax.validation.constraints.NotBlank

/**
 *
 * 관리자가 로그인을 요청하는 AdminSignInRequest
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2023/01/01
 * @version 1.2.5
 **/
data class AdminSignInRequest(

    @field:Range(min = EmployeeNumber.MIN_VALUE, max = EmployeeNumber.MAX_VALUE)
    val employeeNumber: Int,

    @field:NotBlank
    val password: String
) {

    fun toData() = AdminSignInData(
        employeeNumber = employeeNumber,
        password = password
    )
}