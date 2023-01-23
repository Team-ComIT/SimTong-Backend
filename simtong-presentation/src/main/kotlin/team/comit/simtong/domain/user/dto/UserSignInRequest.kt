package team.comit.simtong.domain.user.dto

import team.comit.simtong.domain.user.dto.request.UserSignInData
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 *
 * 일반 사용자가 로그인을 요청하는 UserSignInRequest
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.2.5
 **/
data class UserSignInRequest(

    @field:NotNull
    val employeeNumber: Int?,

    @field:NotBlank
    val password: String?,

    @field:NotBlank
    val deviceToken: String?
) {

    fun toData() = UserSignInData(
        employeeNumber = employeeNumber!!,
        password = password!!,
        deviceToken = deviceToken!!
    )
}