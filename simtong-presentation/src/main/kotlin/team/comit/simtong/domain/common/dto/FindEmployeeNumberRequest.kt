package team.comit.simtong.domain.common.dto

import team.comit.simtong.domain.user.dto.request.FindEmployeeNumberData
import java.util.UUID
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 *
 * 사원 번호 찾기를 요청하는 FindEmployeeNumberRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.2.5
 **/
data class FindEmployeeNumberRequest(

    @field:NotBlank
    val name: String?,

    @field:NotNull
    val spotId: UUID?,

    @field:NotEmpty
    @field:Email
    val email: String?
) {

    fun toData() = FindEmployeeNumberData(
        name = name!!,
        spotId = spotId!!,
        email = email!!
    )
}