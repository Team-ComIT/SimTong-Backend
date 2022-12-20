package team.comit.simtong.domain.team.spi

import team.comit.simtong.domain.team.model.Team
import java.util.UUID

/**
 *
 * 팀에 관한 Query를 요청하는 QueryTeamPort
 *
 * @author kimbeomjin
 * @date 2022/12/20
 * @version 1.0.0
 **/
interface QueryTeamPort {

    fun queryTeamsBySpotId(spotId: UUID): List<Team>

}