package team.comit.simtong.domain.file.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.file.spi.ManageFilePort
import java.io.File

@ExtendWith(SpringExtension::class)
class UploadImageUseCaseTests {

    @MockBean
    private lateinit var managerFilePort: ManageFilePort

    private lateinit var uploadImageUseCase: UploadImageUseCase

    private val filePathStub = "test path"

    private val filePathListStub = listOf(filePathStub)

    private val fileStub: File by lazy {
        File(
            "test.jpg"
        )
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

        // when & then
        assertEquals(uploadImageUseCase.execute(fileStub), filePathStub)
    }

    @Test
    fun `다중 이미지 업로드`() {
        // given
        given(managerFilePort.upload(filesStub))
            .willReturn(filePathListStub)

        // when & then
        assertEquals(uploadImageUseCase.execute(filesStub), filePathListStub)
    }

}