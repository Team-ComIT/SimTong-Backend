package team.comit.simtong.domain.file.error

import team.comit.simtong.global.error.ErrorProperty

enum class FileErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    EXTENSION_INVALID(400, "제한된 확장자(jpg, jpeg, png)"),

    IO_INTERRUPTED(500, "파일 입출력 처리 중단");

    override fun status(): Int = status
    override fun message(): String = message
}