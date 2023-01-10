package team.comit.simtong.domain.team.usecase

import team.comit.simtong.domain.team.dto.QueryTeamsResponse
import team.comit.simtong.domain.team.spi.QueryTeamPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.util.UUID

/**
 *
 * 팀 리스트를 조회하는 QueryTeamsUseCase
 *
 * @author kimbeomjin
 * @date 2022/12/20
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryTeamsUseCase(
    private val queryTeamPort: QueryTeamPort
) {

    fun execute(spotId: UUID): QueryTeamsResponse {
        val teams = queryTeamPort.queryTeamsBySpotId(spotId).map {
            QueryTeamsResponse.TeamElement(
                id = it.id,
                name = it.name
            )
        }

        return QueryTeamsResponse(teams)
    }
}