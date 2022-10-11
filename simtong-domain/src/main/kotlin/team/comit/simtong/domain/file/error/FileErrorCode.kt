package team.comit.simtong.domain.file.error

import team.comit.simtong.global.error.ErrorProperty

/**
 *
 * File Error를 관리하는 FileErrorCode
 *
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
enum class FileErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    INVALID_EXTENSION(400, "제한된 확장자(jpg, jpeg, png)"),

    NOT_FOUND_FILE_PATH(404, "업로드되지않은 파일 경로"),

    IO_INTERRUPTED(500, "파일 입출력 처리 중단");

    override fun status(): Int = status
    override fun message(): String = message
}