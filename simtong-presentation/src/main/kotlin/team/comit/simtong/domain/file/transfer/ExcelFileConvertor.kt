package team.comit.simtong.domain.file.transfer

import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.FileExtensionUtils.XLS
import team.comit.simtong.domain.file.FileExtensionUtils.XLSX

/**
 *
 * Excel File의 변환을 담당하는 ExcelFileConvertor
 *
 * @author Chokyunghyeon
 * @date 2022/12/09
 * @version 1.0.0
 **/
object ExcelFileConvertor : FileConvertor {

    override fun isCorrectExtension(multipartFile: MultipartFile): Boolean {
        return when (multipartFile.extension) {
            XLS, XLSX -> true
            else -> false
        }
    }

}