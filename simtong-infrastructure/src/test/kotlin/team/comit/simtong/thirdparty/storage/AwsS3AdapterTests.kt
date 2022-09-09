package team.comit.simtong.thirdparty.storage

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.util.FileCopyUtils
import team.comit.simtong.domain.file.exception.FileIOInterruptedException
import team.comit.simtong.domain.file.exception.FileInvalidExtensionException
import team.comit.simtong.thirdparty.AwsMockConfig
import java.io.File

@Import(AwsMockConfig::class)
@ExtendWith(SpringExtension::class)
class AwsS3AdapterTests {

    @Autowired
    private lateinit var amazonS3Client: AmazonS3Client

    @Autowired
    private lateinit var awsS3Properties: AwsS3Properties

    private lateinit var awsS3Adapter: AwsS3Adapter

    private val multipartFileStub: MockMultipartFile by lazy {
        MockMultipartFile(
            "test",
            "test.jpg",
            "image/jpg",
            "test".toByteArray()
        )
    }

    @BeforeEach
    fun setUp() {
        awsS3Adapter = AwsS3Adapter(awsS3Properties, amazonS3Client)
    }

    @Test
    fun `S3 테스트`() {
        // given
        val path = "test.jpg"
        val contentType = "image/jpg"
        val objectMetadata = ObjectMetadata().also { it.contentType = contentType }
        val putObjectRequest = PutObjectRequest(awsS3Properties.bucket, path, "".byteInputStream(), objectMetadata)
        amazonS3Client.putObject(putObjectRequest)

        // when
        val result = amazonS3Client.getObject(awsS3Properties.bucket, path)

        // then
        assertThat(result.objectMetadata.contentType).isEqualTo(contentType)
        assertThat(String(FileCopyUtils.copyToByteArray(result.objectContent))).isEqualTo("")
    }

    @Test
    fun `파일 업로드`() {
        // given
        val file = File("test.png")
        multipartFileStub.transferTo(file)

        // when
        val result = awsS3Adapter.upload(file)

        // then
        assertThat(result).contains(awsS3Properties.bucket)
        assertThat(result).contains(file.name)
        assertTrue(file.delete())
    }

    @Test
    fun `파일 확장자 제한`() {
        // given
        val file = File("test.svg")

        // when & then
        assertThrows<FileInvalidExtensionException> {
            awsS3Adapter.upload(file)
        }
    }

    @Test
    fun `파일 입출력 오류`() {
        // given
        val file = File("test.jpg")

        // when & then
        assertThrows<FileIOInterruptedException> {
            awsS3Adapter.upload(file)
        }
    }

}