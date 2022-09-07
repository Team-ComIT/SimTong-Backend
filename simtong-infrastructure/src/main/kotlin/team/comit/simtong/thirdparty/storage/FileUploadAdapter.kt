package team.comit.simtong.thirdparty.storage

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.internal.Mimetypes
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Component
import team.comit.simtong.domain.file.exception.FileExtensionInvalidException
import team.comit.simtong.domain.user.spi.UploadFilePort
import team.comit.simtong.domain.file.exception.FileIOInterruptedException
import java.io.File
import java.io.IOException
import java.util.*

/**
 *
 * 파일 업로드를 시도하는 UploadFileAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/07
 * @version 1.0.0
 **/
@Component
class FileUploadAdapter(
    private val awsProperties: AwsS3Properties,
    private val amazonS3Client: AmazonS3Client
): UploadFilePort {

    override fun fileUpload(file: File): String {
        if(file.extension != "jpa" && file.extension != "jpeg" && file.extension != "png") {
            throw FileExtensionInvalidException.EXCEPTION
        }

        val fileName = "${UUID.randomUUID()}/${file.name}"
        return inputS3(file, fileName)
    }

    private fun inputS3(file: File, fileName: String): String {
        try {
            val inputStream = file.inputStream()
            val objectMetadata = ObjectMetadata()
            objectMetadata.contentType = Mimetypes.getInstance().getMimetype(file)

            amazonS3Client.putObject(PutObjectRequest(awsProperties.bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead))

            return amazonS3Client.getResourceUrl(awsProperties.bucket, fileName)
        } catch (e: IOException) {
            throw FileIOInterruptedException.EXCEPTION
        }
    }
}