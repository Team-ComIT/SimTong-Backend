package team.comit.simtong.domain.file.exception

import team.comit.simtong.domain.file.error.WebFileErrorCode
import team.comit.simtong.global.error.WebException

/**
 *
 * 파일 확장자 제한 에러를 발생시키는 FileInvalidExtensionException
 *
 * @author Chokyunghyeon
 * @date 2022/12/09
 * @version 1.0.0
 **/
class FileInvalidExtensionException private constructor() : WebException(WebFileErrorCode.INVALID_EXTENSION) {

    companion object {
        @JvmField
        val EXCEPTION = FileInvalidExtensionException()
    }

}