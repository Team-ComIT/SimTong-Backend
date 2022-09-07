package team.comit.simtong.domain.user.spi

import java.io.File

/**
 *
 * 파일을 업로드 요청하는 UploadFilePort
 *
 * @author Chokyunghyeon
 * @date 2022/09/07
 * @version 1.0.0
 **/
interface UploadFilePort {

    fun fileUpload(file: File): String
}