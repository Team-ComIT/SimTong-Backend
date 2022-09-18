package team.comit.simtong.domain.spot.exception

import team.comit.simtong.domain.spot.error.SpotErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * Spot Not Found Error를 발생시키는 SpotNotFoundException
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
class SpotNotFoundException private constructor(): BusinessException(SpotErrorCode.SPOT_NOT_FOUND) {

    companion object {
        @JvmField
        val EXCEPTION = SpotNotFoundException()
    }

}