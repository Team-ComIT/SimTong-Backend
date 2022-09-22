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
            "test.png",
            "image/png",
            "test".toByteArray()
        )
    }

    private val jpgFileStub: File by lazy { createFile("test.jpg") }

    private val jpegFileStub: File by lazy { createFile("test.jpeg") }

    private val pngFileStub: File by lazy { createFile("test.png") }

    private val svgFileStub: File by lazy { createFile("test.svg") }

    private fun createFile(name: String): File {
        val file = File(name)
        multipartFileStub.transferTo(file)
        return file
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
    fun `단일 파일 업로드`() {
        // when
        val jpg = awsS3Adapter.upload(jpgFileStub)
        val jpeg = awsS3Adapter.upload(jpegFileStub)
        val png = awsS3Adapter.upload(pngFileStub)

        // then
        assertThat(jpg).contains(awsS3Properties.bucket)
        assertThat(jpeg).contains(awsS3Properties.bucket)
        assertThat(png).contains(awsS3Properties.bucket)
    }

    @Test
    fun `다중 파일 업로드`() {
        // when
        val jpg = awsS3Adapter.upload(listOf(jpgFileStub))
        val jpeg = awsS3Adapter.upload(listOf(jpegFileStub))
        val png = awsS3Adapter.upload(listOf(pngFileStub))

        // then
        jpg.forEach{ assertThat(it).contains(awsS3Properties.bucket) }
        jpeg.forEach{ assertThat(it).contains(awsS3Properties.bucket) }
        png.forEach{ assertThat(it).contains(awsS3Properties.bucket) }
    }

    @Test
    fun `파일 확장자 제한`() {
        // when & then
        assertThrows<FileInvalidExtensionException> {
            awsS3Adapter.upload(svgFileStub)
        }
        assertTrue(svgFileStub.delete())
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