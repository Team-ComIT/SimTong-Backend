package team.comit.simtong.domain.team.dto

import java.util.UUID

/**
 *
 * 팀 리스트를 전송하는 QueryTeamsResponse
 *
 * @author kimbeomjin
 * @date 2022/12/20
 * @version 1.0.0
 **/
data class QueryTeamsResponse(
    val teamList: List<TeamElement>
) {
    data class TeamElement(
        val id: UUID,
        val name: String
    )
}
