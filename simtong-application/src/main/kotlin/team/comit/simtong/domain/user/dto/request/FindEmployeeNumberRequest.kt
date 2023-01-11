package team.comit.simtong.domain.user.dto.request

import java.util.UUID

/**
 *
 * 사원 번호 찾기 정보를 전달하는 FindEmployeeNumberRequest
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.0.0
 **/
data class FindEmployeeNumberRequest(
    val name: String,

    val spotId: UUID,

    val email: String
)