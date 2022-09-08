package team.comit.simtong.domain.file

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.comit.simtong.domain.file.dto.request.WebFileRequest
import team.comit.simtong.domain.file.dto.request.WebMultiFileRequest
import team.comit.simtong.domain.file.dto.response.WebUploadedFileResponse
import team.comit.simtong.domain.file.dto.response.WebUploadedMultiFileResponse
import team.comit.simtong.domain.file.exception.FileIOInterruptedException
import team.comit.simtong.domain.file.usecase.UploadUseCase
import java.io.File
import java.io.IOException
import javax.validation.Valid

/**
 *
 * 파일에 관한 요청을 받는 WebFileAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/08
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/files")
class WebFileAdapter(
    private val uploadUseCase: UploadUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun upload(@ModelAttribute @Valid request: WebFileRequest): WebUploadedFileResponse {
        return WebUploadedFileResponse(
            uploadUseCase.execute(
                transferToFile(request.file)
            ))
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/list")
    fun multiUpload(@ModelAttribute @Valid request: WebMultiFileRequest): WebUploadedMultiFileResponse {
        return WebUploadedMultiFileResponse(
            request.files.map {
                uploadUseCase.execute(
                    transferToFile(it)
                )})
    }

    private fun transferToFile(multipartFile: MultipartFile): File {
        val file = File(multipartFile.originalFilename!!)

        try {
            multipartFile.transferTo(file)
            return file
        } catch (e: IOException) {
            throw FileIOInterruptedException.EXCEPTION
        }
    }

}