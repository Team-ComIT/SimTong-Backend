package team.comit.simtong.domain.spot.exception

import team.comit.simtong.domain.spot.error.SpotErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Different Spot Error를 발생시키는 DifferentSpotException
 *
 * @author Chokyunghyeon
 * @date 2022/11/27
 * @version 1.0.0
 **/
class DifferentSpotException private constructor() : BusinessException(SpotErrorCode.DIFFERENT_SPOT) {

    companion object {
        @JvmField
        val EXCEPTION = DifferentSpotException()
    }

}