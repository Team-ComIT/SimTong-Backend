package team.comit.simtong.domain.user.dto.request

import java.util.UUID

/**
 *
 * 사원 번호 찾기 정보를 전달하는 FindEmployeeNumberData
 *
 * @author Chokyunghyeon
 * @date 2022/09/11
 * @version 1.2.5
 **/
data class FindEmployeeNumberData(
    val name: String,

    val spotId: UUID,

    val email: String
)