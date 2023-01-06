package team.comit.simtong.domain.file.converter

import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.exception.WebFileExceptions
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

/**
 *
 * MultipartFile에서 File의 변환을 담당하는 FileConverter
 *
 * @author Chokyunghyeon
 * @date 2022/12/09
 * @version 1.2.3
 **/
interface FileConverter {

    val MultipartFile.extension: String
        get() = originalFilename?.substringAfterLast(".", "")?.uppercase() ?: ""

    fun isCorrectExtension(multipartFile: MultipartFile): Boolean

    fun transferTo(multipartFile: MultipartFile): File {
        if (!isCorrectExtension(multipartFile)) {
            throw WebFileExceptions.InvalidExtension()
        }

        return transferFile(multipartFile)
    }

    fun transferToList(multipartFiles: List<MultipartFile>): List<File> {
        multipartFiles.forEach {
            if (!isCorrectExtension(it)) {
                throw WebFileExceptions.InvalidExtension()
            }
        }

        return multipartFiles.map(this::transferFile)
    }

    private fun transferFile(multipartFile: MultipartFile): File {
        return File("${UUID.randomUUID()}_${multipartFile.originalFilename}")
            .apply {
                FileOutputStream(this).use {
                    it.write(multipartFile.bytes)
                }
            }
    }

}