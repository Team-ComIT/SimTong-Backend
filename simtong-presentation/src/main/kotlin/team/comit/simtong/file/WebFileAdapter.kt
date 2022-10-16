package team.comit.simtong.file

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.usecase.UploadImageUseCase
import team.comit.simtong.file.dto.response.UploadImageListWebResponse
import team.comit.simtong.file.dto.response.UploadImageWebResponse
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

/**
 *
 * File에 관한 요청을 받는 WebFileAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/21
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/files")
class WebFileAdapter(
    private val uploadImageUseCase: UploadImageUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun uploadSingleImage(file: MultipartFile): UploadImageWebResponse {
        return UploadImageWebResponse(
            uploadImageUseCase.execute(file.let(transferFile))
        )
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/list")
    fun uploadMultipleImage(files: List<MultipartFile>): UploadImageListWebResponse {
        return UploadImageListWebResponse(
            uploadImageUseCase.execute(files.map(transferFile))
        )
    }

    private val transferFile = { multipartFile: MultipartFile ->
        File("${UUID.randomUUID()}@${multipartFile.originalFilename}").let {
            FileOutputStream(it).run {
                this.write(multipartFile.bytes)
                this.close()
            }
            it
        }
    }

}