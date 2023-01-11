package team.comit.simtong.domain.team.usecase

import team.comit.simtong.domain.team.model.Team
import team.comit.simtong.domain.team.spi.QueryTeamPort
import team.comit.simtong.global.annotation.ReadOnlyUseCase
import java.util.UUID

/**
 *
 * 팀 리스트를 조회하는 QueryTeamsUseCase
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
@ReadOnlyUseCase
class QueryTeamsUseCase(
    private val queryTeamPort: QueryTeamPort
) {

    fun execute(spotId: UUID): List<Team> {
        return queryTeamPort.queryTeamsBySpotId(spotId)
    }
}