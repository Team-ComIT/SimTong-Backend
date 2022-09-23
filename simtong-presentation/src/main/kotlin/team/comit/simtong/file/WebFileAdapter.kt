package team.comit.simtong.file

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.usecase.UploadImageUseCase
import team.comit.simtong.file.dto.response.WebUploadImageListResponse
import team.comit.simtong.file.dto.response.WebUploadImageResponse
import java.io.File
import java.io.FileOutputStream

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
    fun singleImage(file: MultipartFile): WebUploadImageResponse {
        return WebUploadImageResponse(
            uploadImageUseCase.execute(file.let(transferFile))
        )
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/list")
    fun multipleImage(files: List<MultipartFile>): WebUploadImageListResponse {
        return WebUploadImageListResponse(
            uploadImageUseCase.execute(files.map(transferFile))
        )
    }

    private val transferFile = { multipartFile: MultipartFile ->
        File(multipartFile.originalFilename!!).let {
            FileOutputStream(it).run {
                this.write(multipartFile.bytes)
                this.close()
            }
            it
        }
    }

}