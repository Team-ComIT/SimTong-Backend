package team.comit.simtong.domain.team.spi

import team.comit.simtong.domain.team.model.Team
import java.util.*

/**
 *
 * Domain에서 Team에 관한 Query를 요청하는 DomainQueryTeamPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
interface DomainQueryTeamPort {

    fun queryTeamById(id: UUID): Team?

    fun queryTeamByName(name: String): Team?

}