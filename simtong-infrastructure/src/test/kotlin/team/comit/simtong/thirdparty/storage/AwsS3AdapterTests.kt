package team.comit.simtong.thirdparty.storage

import com.amazonaws.services.s3.AmazonS3Client
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.file.exception.FileExceptions
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
    fun `단일 파일 업로드`() {
        // given
        val file = createFile("test.png")

        // when
        val result = awsS3Adapter.upload(file)

        // then
        assertThat(result).contains(awsS3Properties.bucket)
    }

    @Test
    fun `다중 파일 업로드`() {
        // given
        val files = listOf(
            createFile("test.jpg"),
            createFile("test.jpeg"),
            createFile("test.png")
        )

        // when
        val result = awsS3Adapter.upload(files)

        // then
        result.forEach{
            assertThat(it).contains(awsS3Properties.bucket)
        }
    }

    @Test
    fun `단일 파일 입출력 오류`() {
        // given
        val file = File("test.jpg")

        // when & then
        assertThrows<FileExceptions.IOInterrupted> {
            awsS3Adapter.upload(file)
        }
    }

    @Test
    fun `다중 파일 입출력 오류`() {
        // given
        val files = listOf(
            File("test.jpg"),
            File("test.jpeg"),
            File("test.png")
        )

        // when & then
        assertThrows<FileExceptions.IOInterrupted> {
            awsS3Adapter.upload(files)
        }
    }

    @Test
    fun `파일 경로 검사`() {
        //given
        val file = createFile("test.png")

        // when
        awsS3Adapter.upload(file)

        val correct = awsS3Adapter.existsPath("/test.png")
        val wrong = awsS3Adapter.existsPath("/test.svg")

        // then
        assertTrue(correct)
        assertFalse(wrong)
    }

}