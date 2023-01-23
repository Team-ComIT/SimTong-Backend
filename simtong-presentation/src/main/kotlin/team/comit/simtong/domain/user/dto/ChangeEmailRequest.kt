package team.comit.simtong.domain.user.dto

import team.comit.simtong.domain.user.dto.request.ChangeEmailData
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

/**
 *
 * 이메일 변경을 요청하는 ChangeEmailRequest
 *
 * @author Chokyunghyeon
 * @date 2022/10/03
 * @version 1.0.0
 **/
data class ChangeEmailRequest(

    @field:NotEmpty
    @field:Email
    private val email: String?
) {

    fun toData() = ChangeEmailData(
        email = email!!
    )
}