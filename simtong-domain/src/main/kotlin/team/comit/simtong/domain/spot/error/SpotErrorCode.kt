package team.comit.simtong.domain.spot.error

import team.comit.simtong.global.error.ErrorProperty

/**
 *
 * Spot Error를 관리하는 SpotErrorCode
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
enum class SpotErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    // 404
    SPOT_NOT_FOUND(404, "지점을 찾을 수 없음");

    override fun status(): Int = status

    override fun message(): String = message
}