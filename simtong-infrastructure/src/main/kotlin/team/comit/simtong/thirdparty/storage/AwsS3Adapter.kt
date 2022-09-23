package team.comit.simtong.thirdparty.storage

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.internal.Mimetypes
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Component
import team.comit.simtong.domain.file.exception.FileIOInterruptedException
import team.comit.simtong.domain.file.spi.ManageFilePort
import java.io.File
import java.io.IOException
import java.util.UUID

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
        val fileName = "${UUID.randomUUID()}@${file.name}"
        inputS3(file, fileName)

        return getResource(fileName)
    }

    override fun upload(files: List<File>): List<String> {
        return files.map {
                val fileName = "${UUID.randomUUID()}@${it.name}"
                inputS3(it, fileName)

                getResource(fileName)
            }
    }

    private fun inputS3(file: File, fileName: String) {
        try {
            val inputStream = file.inputStream()
            val objectMetadata = ObjectMetadata().apply {
                this.contentLength = file.length()
                this.contentType = Mimetypes.getInstance().getMimetype(file)
            }

            amazonS3Client.putObject(PutObjectRequest(awsProperties.bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead))
            file.delete()
        } catch (e: IOException) {
            throw FileIOInterruptedException.EXCEPTION
        }
    }

    private fun getResource(fileName: String): String {
        return amazonS3Client.getResourceUrl(awsProperties.bucket, fileName)
    }

}