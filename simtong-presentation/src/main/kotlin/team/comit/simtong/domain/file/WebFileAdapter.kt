package team.comit.simtong.domain.file

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.dto.response.UploadImageListWebResponse
import team.comit.simtong.domain.file.dto.response.UploadImageWebResponse
import team.comit.simtong.domain.file.transfer.ExcelFileConvertor
import team.comit.simtong.domain.file.transfer.ImageFileConvertor
import team.comit.simtong.domain.file.usecase.RegisterEmployeeCertificateUseCase
import team.comit.simtong.domain.file.usecase.UploadImageUseCase

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
            uploadImageUseCase.execute(
                file.let(ImageFileConvertor::transferTo)
            )
        )
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadMultipleImage(files: List<MultipartFile>): UploadImageListWebResponse {
        return UploadImageListWebResponse(
            uploadImageUseCase.execute(
                files.let(ImageFileConvertor::transferToList)
            )
        )
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    fun importEmployeeCertificate(file: MultipartFile) {
        registerEmployeeCertificateUseCase.execute(
            file.let(ExcelFileConvertor::transferTo)
        )
    }

}