package team.comit.simtong.global.exception

import team.comit.simtong.global.error.BusinessException
import team.comit.simtong.global.error.GlobalErrorCode

/**
 *
 * Bad Request를 발생시키는 BadRequestException
 *
 * @author kimbeomjin
 * @date 2022/08/22
 * @version 1.0.0
 **/
class BadRequestException private constructor() : BusinessException(GlobalErrorCode.BAD_REQUEST) {

    companion object {
        @JvmField
        val EXCEPTION = BadRequestException()
    }
}