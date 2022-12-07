package team.comit.simtong.domain.file.exception

import team.comit.simtong.domain.file.error.FileErrorCode
import team.comit.simtong.global.error.BusinessException

object InvalidEmployeeException : BusinessException(
    FileErrorCode.INVALID_EMPLOYEE
)