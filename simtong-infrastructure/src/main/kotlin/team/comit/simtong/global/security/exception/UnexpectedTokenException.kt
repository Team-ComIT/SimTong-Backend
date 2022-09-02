package team.comit.simtong.global.security.exception

import team.comit.simtong.global.error.BusinessException
import team.comit.simtong.global.security.error.SecurityErrorCode

/**
  *
  * 형식이 잘못된 토큰이 들어올 경우
  * Unexpected Token Error을 발생시키는 UnexpectedTokenException
  *
  * @author JoKyungHyeon
  * @date 2022/09/02
  * @version 1.0.0
 **/
class UnexpectedTokenException private constructor() : BusinessException(SecurityErrorCode.UNEXPECTED_TOKEN) {

    companion object {
        @JvmField
        val EXCEPTION = UnexpectedTokenException()
    }
}