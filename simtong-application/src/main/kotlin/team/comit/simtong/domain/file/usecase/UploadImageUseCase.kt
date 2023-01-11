package team.comit.simtong.domain.file.usecase

import team.comit.simtong.domain.file.spi.UploadFilePort
import team.comit.simtong.domain.file.dto.response.UploadImageListResponse
import team.comit.simtong.domain.file.dto.response.UploadImageResponse
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
    private val uploadFilePort: UploadFilePort
) {

    fun execute(file: File): UploadImageResponse {
        return uploadFilePort.upload(file)
            .let(::UploadImageResponse)
    }

    fun execute(files: List<File>): UploadImageListResponse {
        return uploadFilePort.upload(files)
            .let(::UploadImageListResponse)
    }
}