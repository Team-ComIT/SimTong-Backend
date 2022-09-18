package team.comit.simtong.domain.team.exception

import team.comit.simtong.domain.team.error.TeamErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Team Not Found Error를 발생시키는 TeamNotFoundException
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
class TeamNotFoundException private constructor() : BusinessException(TeamErrorCode.TEAM_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = TeamNotFoundException()
    }

}