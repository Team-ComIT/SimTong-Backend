package team.comit.simtong.domain.file

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.converter.ExcelFileConverter
import team.comit.simtong.domain.file.converter.ImageFileConverter
import team.comit.simtong.domain.file.dto.response.UploadImageListResponse
import team.comit.simtong.domain.file.dto.response.UploadImageResponse
import team.comit.simtong.domain.file.usecase.RegisterEmployeeCertificateUseCase
import team.comit.simtong.domain.file.usecase.UploadImageUseCase
import javax.validation.constraints.NotNull

/**
 *
 * File에 관한 요청을 받는 WebFileAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/21
 * @version 1.2.5
 **/
@Validated
@RestController
@RequestMapping("/files")
class WebFileAdapter(
    private val uploadImageUseCase: UploadImageUseCase,
    private val registerEmployeeCertificateUseCase: RegisterEmployeeCertificateUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadSingleImage(@NotNull @RequestPart file: MultipartFile?): UploadImageResponse {
        return uploadImageUseCase.execute(
            file!!.let(ImageFileConverter::transferTo)
        )
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadMultipleImage(@NotNull @RequestPart files: List<MultipartFile>?): UploadImageListResponse {
        return uploadImageUseCase.execute(
            files!!.let(ImageFileConverter::transferToList)
        )
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    fun importEmployeeCertificate(@NotNull @RequestPart file: MultipartFile?) {
        registerEmployeeCertificateUseCase.execute(
            file!!.let(ExcelFileConverter::transferTo)
        )
    }

}