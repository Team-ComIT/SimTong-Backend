package team.comit.simtong.domain.file.converter

import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.converter.FileExtensions.XLS
import team.comit.simtong.domain.file.converter.FileExtensions.XLSX

/**
 *
 * Excel File의 변환을 담당하는 ExcelFileConverter
 *
 * @author Chokyunghyeon
 * @date 2022/12/09
 * @version 1.0.0
 **/
object ExcelFileConverter : FileConverter {

    override fun isCorrectExtension(multipartFile: MultipartFile): Boolean {
        return when (multipartFile.extension) {
            XLS, XLSX -> true
            else -> false
        }
    }

}