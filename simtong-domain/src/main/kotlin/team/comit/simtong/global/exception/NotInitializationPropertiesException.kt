package team.comit.simtong.global.exception

import team.comit.simtong.global.error.BusinessException
import team.comit.simtong.global.error.DomainErrorCode

/**
 *
 * Domain Properties 초기화 에러를 발생시키는 NotInitializationPropertiesException
 *
 * @author Chokyunghyeon
 * @date 2022/11/26
 * @version 1.0.0
 **/
object NotInitializationPropertiesException : BusinessException(DomainErrorCode.NOT_INITIALIZATION_PROPERTIES)