package team.comit.simtong.domain.file.usecase

import team.comit.simtong.domain.file.spi.ManageFilePort
import team.comit.simtong.global.annotation.UseCase
import java.io.File

/**
 *
 * 파일 업로드를 담당하는 UploadUseCase
 *
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
@UseCase
class UploadUseCase(
    private val manageFilePort: ManageFilePort
) {

    fun execute(file: File) = manageFilePort.upload(file)

}