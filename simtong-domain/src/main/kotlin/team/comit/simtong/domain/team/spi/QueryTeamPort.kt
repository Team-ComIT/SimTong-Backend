package team.comit.simtong.domain.team.spi

import team.comit.simtong.domain.team.model.Team
import java.util.UUID

interface QueryTeamPort {

    fun queryTeamsBySpotId(spotId: UUID): List<Team>

}