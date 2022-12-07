package team.comit.simtong.domain.file.exception

import team.comit.simtong.domain.file.error.FileErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * DB에 저장된 일치하는 사원이 없는 경우 발생시키는 InvalidEmployeeException
 *
 * @author kimbeomjin
 * @date 2022/12/07
 * @version 1.0.0
 **/
object InvalidEmployeeException : BusinessException(
    FileErrorCode.INVALID_EMPLOYEE
)