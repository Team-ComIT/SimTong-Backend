package team.comit.simtong.domain.admin.dto

import team.comit.simtong.domain.user.dto.request.AdminSignInData
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

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

    @field:NotNull
    private val employeeNumber: Int?,

    @field:NotBlank
    private val password: String?
) {

    fun toData() = AdminSignInData(
        employeeNumber = employeeNumber!!,
        password = password!!
    )
}