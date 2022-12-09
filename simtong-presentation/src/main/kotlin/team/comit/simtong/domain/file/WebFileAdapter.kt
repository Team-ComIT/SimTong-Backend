package team.comit.simtong.domain.file

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.dto.response.UploadImageListWebResponse
import team.comit.simtong.domain.file.dto.response.UploadImageWebResponse
import team.comit.simtong.domain.file.usecase.RegisterEmployeeCertificateUseCase
import team.comit.simtong.domain.file.usecase.UploadImageUseCase
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
    private val uploadImageUseCase: UploadImageUseCase,
    private val registerEmployeeCertificateUseCase: RegisterEmployeeCertificateUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadSingleImage(file: MultipartFile): UploadImageWebResponse {
        return UploadImageWebResponse(
            uploadImageUseCase.execute(file.let(transferFile))
        )
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadMultipleImage(files: List<MultipartFile>): UploadImageListWebResponse {
        return UploadImageListWebResponse(
            uploadImageUseCase.execute(files.map(transferFile))
        )
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    fun importEmployeeCertificate(file: MultipartFile) {
        registerEmployeeCertificateUseCase.execute(file.let(transferFile))
    }

    private val transferFile = { multipartFile: MultipartFile ->
        File("${UUID.randomUUID()}_${multipartFile.originalFilename}").apply {
            FileOutputStream(this).run {
                write(multipartFile.bytes)
                close()
            }
        }
    }

}