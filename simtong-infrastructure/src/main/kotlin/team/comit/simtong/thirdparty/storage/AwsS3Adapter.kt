package team.comit.simtong.thirdparty.storage

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.internal.Mimetypes
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Component
import team.comit.simtong.domain.file.exception.FileIOInterruptedException
import team.comit.simtong.domain.file.spi.CheckFilePort
import team.comit.simtong.domain.file.spi.UploadFilePort
import java.io.File
import java.io.IOException

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
): UploadFilePort, CheckFilePort {

    override fun upload(file: File): String {
        inputS3(file, file.name)

        return getResource(file.name)
    }

    override fun upload(files: List<File>): List<String> {
        return files.map {
                inputS3(it, it.name)
                getResource(it.name)
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

    override fun existsPath(path: String): Boolean {
        return amazonS3Client.doesObjectExist(awsProperties.bucket, path.substringAfterLast('/', ""))
    }

}