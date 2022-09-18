package team.comit.simtong.domain.team.error

import team.comit.simtong.global.error.ErrorProperty

/**
 *
 * Team에 대해 발생하는 Error를 관리하는 TeamErrorCode
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
enum class TeamErrorCode(
    private val status: Int,
    private val message: String
): ErrorProperty {

    // 404
    TEAM_NOT_FOUND(404, "팀을 찾을 수 없음");

    override fun status(): Int = status

    override fun message(): String = message

}