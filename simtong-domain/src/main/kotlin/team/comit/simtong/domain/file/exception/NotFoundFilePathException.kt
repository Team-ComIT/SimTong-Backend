package team.comit.simtong.domain.file.exception

import team.comit.simtong.domain.file.error.FileErrorCode
import team.comit.simtong.global.error.BusinessException

/**
 *
 * 파일 경로를 찾을 수 없음 에러를 발생시키는 NotFoundFilePathException
 *
 * @author Chokyunghyeon
 * @date 2022/12/08
 * @version 1.0.0
 **/
class NotFoundFilePathException private constructor() : BusinessException(FileErrorCode.NOT_FOUND_FILE_PATH) {

    companion object {
        @JvmField
        val EXCEPTION = NotFoundFilePathException()
    }

}