package team.comit.simtong.domain.file

import java.io.File

/**
 *
 * 파일 확장자를 관리하는 FileExtensionUtils
 *
 * @author Chokyunghyeon
 * @date 2022/12/06
 * @version 1.0.0
 **/
object FileExtensionUtils {

    const val JPG = "JPG"
    const val JPEG = "JPEG"
    const val PNG = "PNG"

    /**
     * Excel 2003 이전 형식 확장자
     */
    const val XLS = "XLS"

    /**
     * Excel 2007 이후 형식 확장자
     */
    const val XLSX = "XLSX"

    fun isImageExtension(file: File) = when (file.extension.uppercase()) {
        JPG, JPEG, PNG -> true
        else -> false
    }

}