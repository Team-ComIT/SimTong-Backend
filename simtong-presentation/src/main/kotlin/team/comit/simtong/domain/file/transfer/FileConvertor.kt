package team.comit.simtong.domain.file.transfer

import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.exception.FileInvalidExtensionException
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

/**
 *
 * MultipartFile에서 File의 변환을 담당하는 FileConvertor
 *
 * @author Chokyunghyeon
 * @date 2022/12/09
 * @version 1.0.0
 **/
interface FileConvertor {

    val MultipartFile.extension: String
        get() = originalFilename?.substringAfterLast(".", "")?.uppercase() ?: ""


    fun isCorrectExtension(multipartFile: MultipartFile): Boolean

    fun transferTo(multipartFile: MultipartFile): File {
        if (!isCorrectExtension(multipartFile)) {
            throw FileInvalidExtensionException.EXCEPTION
        }

        return transferFile(multipartFile)
    }

    fun transferToList(multipartFiles: List<MultipartFile>): List<File> {
        multipartFiles.forEach {
            if (!isCorrectExtension(it)) {
                throw FileInvalidExtensionException.EXCEPTION
            }
        }

        return multipartFiles.map(this::transferFile)
    }

    private fun transferFile(multipartFile: MultipartFile): File {
        return File("${UUID.randomUUID()}_${multipartFile.originalFilename}")
            .apply {
                FileOutputStream(this).run {
                    write(multipartFile.bytes)
                    close()
                }
            }
    }

}