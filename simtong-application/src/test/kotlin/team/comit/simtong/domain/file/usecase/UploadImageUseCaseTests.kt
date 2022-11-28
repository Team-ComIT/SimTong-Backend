package team.comit.simtong.domain.file.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.file.exception.FileInvalidExtensionException
import team.comit.simtong.domain.file.spi.UploadFilePort
import team.comit.simtong.global.annotation.SimtongTest
import java.io.File

@SimtongTest
class UploadImageUseCaseTests {

    @MockBean
    private lateinit var managerFilePort: UploadFilePort

    private lateinit var uploadImageUseCase: UploadImageUseCase

    private val filePathStub = "test path"

    private val filePathListStub = listOf(filePathStub)

    private val jpgFileStub by lazy { File("test.jpg") }

    private val jpegFileStub by lazy { File("test.jpeg") }

    private val pngFileStub by lazy { File("test.png") }

    private val svgFileStub by lazy { File("test.svg") }

    @BeforeEach
    fun setUp() {
        uploadImageUseCase = UploadImageUseCase(managerFilePort)
    }

    @Test
    fun `단일 이미지 업로드`() {
        // given
        given(managerFilePort.upload(jpgFileStub))
            .willReturn(filePathStub)

        given(managerFilePort.upload(jpegFileStub))
            .willReturn(filePathStub)

        given(managerFilePort.upload(pngFileStub))
            .willReturn(filePathStub)

        // when & then
        assertEquals(uploadImageUseCase.execute(jpgFileStub), filePathStub)
        assertEquals(uploadImageUseCase.execute(jpegFileStub), filePathStub)
        assertEquals(uploadImageUseCase.execute(pngFileStub), filePathStub)
    }

    @Test
    fun `다중 이미지 업로드`() {
        // given
        val filesStub = listOf(jpgFileStub, jpegFileStub, pngFileStub)

        given(managerFilePort.upload(filesStub))
            .willReturn(filePathListStub)

        // when & then
        assertEquals(uploadImageUseCase.execute(filesStub), filePathListStub)
    }

    @Test
    fun `단일 파일 확장자 오류`() {
        // when & then
        assertThrows<FileInvalidExtensionException> {
            uploadImageUseCase.execute(svgFileStub)
        }
    }

    @Test
    fun `다중 파일 확장자 오류`() {
        // given
        val files = listOf(jpgFileStub, jpegFileStub, pngFileStub, svgFileStub)

        // when & then
        assertThrows<FileInvalidExtensionException> {
            uploadImageUseCase.execute(files)
        }
    }

}