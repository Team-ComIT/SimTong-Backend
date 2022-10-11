package team.comit.simtong.domain.file.exception

import team.comit.simtong.domain.file.error.FileErrorCode
import team.comit.simtong.global.error.BusinessException

class NotFoundFilePathException private constructor() : BusinessException(FileErrorCode.NOT_FOUND_FILE_PATH) {

    companion object {
        @JvmField
        val EXCEPTION = NotFoundFilePathException()
    }

}