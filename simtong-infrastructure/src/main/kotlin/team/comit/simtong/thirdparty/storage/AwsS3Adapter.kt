package team.comit.simtong.thirdparty.storage

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.internal.Mimetypes
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Component
import team.comit.simtong.domain.file.exception.FileInvalidExtensionException
import team.comit.simtong.domain.file.spi.ManageFilePort
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
class AwsS3Adapter(
    private val awsProperties: AwsS3Properties,
    private val amazonS3Client: AmazonS3Client
): ManageFilePort {

    override fun upload(file: File): String {
        if(file.extension != "jpa" && file.extension != "jpeg" && file.extension != "png") {
            throw FileInvalidExtensionException.EXCEPTION
        }

        val fileName = "${UUID.randomUUID()}${file.name}"
        inputS3(file, fileName)

        return getResource(fileName)
    }

    private fun inputS3(file: File, fileName: String) {
        try {
            val inputStream = file.inputStream()
            val objectMetadata = ObjectMetadata()
            objectMetadata.contentType = Mimetypes.getInstance().getMimetype(file)

            amazonS3Client.putObject(PutObjectRequest(awsProperties.bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead))
        } catch (e: IOException) {
            throw FileIOInterruptedException.EXCEPTION
        }
    }

    private fun getResource(fileName: String): String {
        return amazonS3Client.getResourceUrl(awsProperties.bucket, fileName)
    }
}