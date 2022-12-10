package team.comit.simtong.domain.file.convertor

import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.convertor.FileExtensions.HEIC
import team.comit.simtong.domain.file.convertor.FileExtensions.JPEG
import team.comit.simtong.domain.file.convertor.FileExtensions.JPG
import team.comit.simtong.domain.file.convertor.FileExtensions.PNG

/**
 *
 * Image File의 변환을 담당하는 ImageFileConverter
 *
 * @author Chokyunghyeon
 * @date 2022/12/09
 * @version 1.0.0
 **/
object ImageFileConverter : FileConverter {

    override fun isCorrectExtension(multipartFile: MultipartFile): Boolean {
        return when (multipartFile.extension) {
            JPG, JPEG, PNG, HEIC -> true
            else -> false
        }
    }

}