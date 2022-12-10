package team.comit.simtong.domain.file.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.file.model.EmployeeCertificate
import team.comit.simtong.domain.file.spi.CommandEmployeeCertificatePort
import team.comit.simtong.domain.file.spi.ParseEmployeeCertificateFilePort
import team.comit.simtong.global.annotation.SimtongTest
import java.io.File

@SimtongTest
class RegisterEmployeeCertificateUseCaseTest {

    @MockBean
    private lateinit var commandEmployeeCertificatePort: CommandEmployeeCertificatePort

    @MockBean
    private lateinit var parseEmployeeCertificateFilePort: ParseEmployeeCertificateFilePort

    private lateinit var registerEmployeeCertificateUseCase: RegisterEmployeeCertificateUseCase

    private val fileStub = File("")

    private val employeeCertificateList: List<EmployeeCertificate> by lazy {
        listOf(
            EmployeeCertificate(
                employeeNumber = 1234567890,
                name = "test name",
                spotName = "test spot name",
                teamName = "test team name"
            )
        )
    }

    @BeforeEach
    fun setUp() {
        registerEmployeeCertificateUseCase = RegisterEmployeeCertificateUseCase(
            commandEmployeeCertificatePort = commandEmployeeCertificatePort,
            parseEmployeeCertificateFilePort = parseEmployeeCertificateFilePort
        )
    }

    @Test
    fun `사원 명부 등록 성공`() {
        // given
        given(parseEmployeeCertificateFilePort.importEmployeeCertificate(fileStub))
            .willReturn(employeeCertificateList)

        // when & then
        assertDoesNotThrow {
            registerEmployeeCertificateUseCase.execute(fileStub)
        }
    }
}