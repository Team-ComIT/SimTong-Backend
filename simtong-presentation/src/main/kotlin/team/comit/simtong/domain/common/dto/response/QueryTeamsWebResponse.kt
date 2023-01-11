package team.comit.simtong.domain.common.dto.response

import java.util.UUID

/**
 *
 * 팀 리스트를 전송하는 QueryTeamsWebResponse
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.2.5
 **/
data class QueryTeamsWebResponse(
    val teamList: List<TeamElement>
) {
    data class TeamElement(
        val id: UUID,
        val name: String
    )
}
