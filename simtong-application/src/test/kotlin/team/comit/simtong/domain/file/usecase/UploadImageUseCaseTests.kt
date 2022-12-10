package team.comit.simtong.domain.file.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.file.spi.UploadFilePort
import team.comit.simtong.global.annotation.SimtongTest
import java.io.File

@SimtongTest
class UploadImageUseCaseTests {

    @MockBean
    private lateinit var managerFilePort: UploadFilePort

    private lateinit var uploadImageUseCase: UploadImageUseCase

    private val filePathStub = "test path"

    private val fileStub: File by lazy {
        File("test.jpg")
    }

    private val filesStub: List<File> by lazy {
        listOf(fileStub)
    }

    @BeforeEach
    fun setUp() {
        uploadImageUseCase = UploadImageUseCase(managerFilePort)
    }

    @Test
    fun `단일 이미지 업로드`() {
        // given
        given(managerFilePort.upload(fileStub))
            .willReturn(filePathStub)

        // when
        val response = uploadImageUseCase.execute(fileStub)

        // then
        assertEquals(response, filePathStub)

    }

    @Test
    fun `다중 이미지 업로드`() {
        // given
        val filePathListStub = listOf(filePathStub)

        given(managerFilePort.upload(filesStub))
            .willReturn(filePathListStub)

        // when
        val response = uploadImageUseCase.execute(filesStub)

        assertEquals(response, filePathListStub)
    }

}