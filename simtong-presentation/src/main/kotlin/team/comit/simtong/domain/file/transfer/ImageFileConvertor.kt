package team.comit.simtong.domain.file.transfer

import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.FileExtensionUtils.HEIC
import team.comit.simtong.domain.file.FileExtensionUtils.JPEG
import team.comit.simtong.domain.file.FileExtensionUtils.JPG
import team.comit.simtong.domain.file.FileExtensionUtils.PNG

/**
 *
 * Image File의 변환을 담당하는 ImageFileConvertor
 *
 * @author Chokyunghyeon
 * @date 2022/12/09
 * @version 1.0.0
 **/
object ImageFileConvertor : FileConvertor {

    override fun isCorrectExtension(multipartFile: MultipartFile): Boolean {
        return when (multipartFile.extension) {
            JPG, JPEG, PNG, HEIC -> true
            else -> false
        }
    }

}