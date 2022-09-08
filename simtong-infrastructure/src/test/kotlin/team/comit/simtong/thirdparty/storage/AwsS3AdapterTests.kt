package team.comit.simtong.thirdparty.storage

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import io.findify.s3mock.S3Mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.util.FileCopyUtils
import team.comit.simtong.domain.file.exception.FileInvalidExtensionException
import team.comit.simtong.thirdparty.AwsMockConfig
import java.io.File

@Import(AwsMockConfig::class)
@ExtendWith(SpringExtension::class)
class AwsS3AdapterTests {

    @Autowired
    private lateinit var s3Mock: S3Mock

    @Autowired
    private lateinit var amazonS3: AmazonS3

    @MockBean
    private lateinit var awsS3Adapter: AwsS3Adapter

    private val bucket: String = "simtong"

    @AfterEach
    fun tearDown() {
        s3Mock.stop()
    }

    @Test
    fun `S3 테스트`() {
        // given
        val path = "test.jpg"
        val contentType = "image/jpg"
        val objectMetadata = ObjectMetadata().also { it.contentType = contentType }
        val putObjectRequest = PutObjectRequest(bucket, path, "".byteInputStream(), objectMetadata)
        amazonS3.putObject(putObjectRequest)

        // when
        val result = amazonS3.getObject(bucket, path)

        // then
        assertThat(result.objectMetadata.contentType).isEqualTo(contentType)
        assertThat(String(FileCopyUtils.copyToByteArray(result.objectContent))).isEqualTo("")

    }

    @Test
    fun `파일 업로드`() {
        // given
        val file = File("test.jpg")

        // when
        val result = awsS3Adapter.upload(file)

        // then
        assertNotNull(result)
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

}