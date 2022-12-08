package team.comit.simtong.domain.file.exception

import team.comit.simtong.domain.file.error.FileErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * 파일의 내용이 올바르지 않음 에러를 발생시키는 FileNotValidContentException
 *
 * @author Chokyunghyeon
 * @date 2022/12/07
 * @version 1.0.0
 **/
class FileNotValidContentException private constructor() : BusinessException(FileErrorCode.NOT_VALID_CONTENT) {

    companion object {
        @JvmField
        val EXCEPTION = FileNotValidContentException()
    }
}