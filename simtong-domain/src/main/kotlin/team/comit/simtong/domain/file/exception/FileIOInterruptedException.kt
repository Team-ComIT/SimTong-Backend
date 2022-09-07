package team.comit.simtong.domain.file.exception

import team.comit.simtong.domain.file.error.FileErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * 파일 입출력 처리 오류를 발생시키는 FileUploadFailedException
 *
 * @author Chokyunghyeon
 * @date 2022/09/07
 * @version 1.0.0
 **/
class FileIOInterruptedException private constructor() : BusinessException(FileErrorCode.IO_INTERRUPTED) {

    companion object {
        val EXCEPTION = FileIOInterruptedException()
    }

}