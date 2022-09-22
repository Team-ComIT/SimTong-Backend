package team.comit.simtong.domain.file.usecase

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

    fun execute(file: File): String = manageFilePort.upload(file)

    fun execute(files: List<File>): List<String> = manageFilePort.upload(files)
    
}