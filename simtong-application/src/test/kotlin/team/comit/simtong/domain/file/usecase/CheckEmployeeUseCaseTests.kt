package team.comit.simtong.domain.file.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.file.exception.InvalidEmployeeException
import team.comit.simtong.domain.file.spi.QueryEmployeeCertificatePort
import team.comit.simtong.global.annotation.SimtongTest

@SimtongTest
class CheckEmployeeUseCaseTests {

    @MockBean
    private lateinit var queryEmployeeCertificatePort: QueryEmployeeCertificatePort

    private lateinit var checkEmployeeUseCase: CheckEmployeeUseCase

    private val name = "test name"

    private val employeeNumber = 1234567890

    @BeforeEach
    fun setUp() {
        checkEmployeeUseCase = CheckEmployeeUseCase(queryEmployeeCertificatePort)
    }

    @Test
    fun `사원 확인 성공`() {
        // given
        given(queryEmployeeCertificatePort.existsEmployeeCertificateByNameAndEmployeeNumber(name, employeeNumber))
            .willReturn(true)

        // when & then
        assertDoesNotThrow {
            checkEmployeeUseCase.execute(name, employeeNumber)
        }
    }

    @Test
    fun `일치하는 사원이 존재하지 않음`() {
        // given
        given(queryEmployeeCertificatePort.existsEmployeeCertificateByNameAndEmployeeNumber(name, employeeNumber))
            .willReturn(false)

        // when & then
        assertThrows<InvalidEmployeeException> {
            checkEmployeeUseCase.execute(name, employeeNumber)
        }
    }
}