package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.team.model.Team

/**
 *
 * User Domain에서 Team Domain에 관한 Query를 요청하는 DomainQueryTeamPort
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
interface UserQueryTeamPort {

    fun queryTeamByName(name: String): Team?

}