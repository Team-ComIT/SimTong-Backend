package team.comit.simtong.global.exception

import team.comit.simtong.global.error.BusinessException
import team.comit.simtong.global.error.GlobalErrorCode

/**
 *
 * Internal Server Error를 발생시키는 InternalServerErrorException
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
class InternalServerErrorException private constructor() : BusinessException(GlobalErrorCode.INTERNAL_SERVER_ERROR) {

    companion object {
        @JvmField
        val EXCEPTION = InternalServerErrorException()
    }
}