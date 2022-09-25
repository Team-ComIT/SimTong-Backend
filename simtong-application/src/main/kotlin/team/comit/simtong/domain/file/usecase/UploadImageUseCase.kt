package team.comit.simtong.domain.file.usecase

import team.comit.simtong.domain.file.exception.FileInvalidExtensionException
import team.comit.simtong.domain.file.spi.ManageFilePort
import team.comit.simtong.global.annotation.UseCase
import java.io.File

/**
 *
 * 이미지 업로드 기능을 담당하는 UploadImageUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/20
 * @version 1.0.0
 **/
@UseCase
class UploadImageUseCase(
    private val manageFilePort: ManageFilePort
) {

    fun execute(file: File): String {
        if (!isCorrectExtension(file)) {
            file.delete()
            throw FileInvalidExtensionException.EXCEPTION
        }

        return manageFilePort.upload(file)
    }

    fun execute(files: List<File>): List<String> {
        files.forEach {
            if (!isCorrectExtension(it)) {
                files.deleteAll()
                throw FileInvalidExtensionException.EXCEPTION
            }
        }

        return manageFilePort.upload(files)
    }

    private fun isCorrectExtension(file: File) = when (file.extension) {
        "jpg", "jpeg", "png" -> true
        else -> false
    }

    private fun List<File>.deleteAll() = this.forEach(File::delete)

}