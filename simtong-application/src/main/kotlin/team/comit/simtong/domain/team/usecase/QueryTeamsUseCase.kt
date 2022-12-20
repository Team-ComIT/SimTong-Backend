package team.comit.simtong.domain.team.usecase

import team.comit.simtong.domain.team.dto.QueryTeamsResponse
import team.comit.simtong.domain.team.spi.QueryTeamPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryTeamsUseCase(
    private val queryTeamPort: QueryTeamPort
) {

    fun execute(spotId: UUID): QueryTeamsResponse {
        val teams = queryTeamPort.queryTeamsBySpotId(spotId)

        return QueryTeamsResponse(
            teams.map {
                QueryTeamsResponse.TeamElement(
                    id = it.id,
                    name = it.name
                )
            }
        )
    }
}