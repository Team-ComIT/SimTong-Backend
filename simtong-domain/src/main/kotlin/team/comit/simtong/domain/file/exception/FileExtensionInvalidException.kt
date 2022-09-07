package team.comit.simtong.domain.file.exception

import team.comit.simtong.domain.file.error.FileErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * 확장자 제한 에러를 발생시키는 FileExtensionInvalidException
 *
 * @author Chokyunghyeon
 * @date 2022/09/07
 * @version 1.0.0
 **/
class FileExtensionInvalidException private constructor() : BusinessException(FileErrorCode.EXTENSION_INVALID) {

    companion object {
        val EXCEPTION = FileExtensionInvalidException()
    }

}