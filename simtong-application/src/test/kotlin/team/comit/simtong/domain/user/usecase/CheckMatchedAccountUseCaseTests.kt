package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.global.annotation.SimtongTest

@SimtongTest
class CheckMatchedAccountUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    private lateinit var checkMatchedAccountUseCase: CheckMatchedAccountUseCase

    private val employeeNumber = 1234567890

    private val email = "test@test.com"

    @BeforeEach
    fun setUp() {
        checkMatchedAccountUseCase = CheckMatchedAccountUseCase(queryUserPort)
    }

    @Test
    fun `계정 확인 성공`() {
        // given
        given(queryUserPort.existsUserByEmployeeNumberAndEmail(employeeNumber, email))
            .willReturn(true)

        // when & then
        assertDoesNotThrow {
            checkMatchedAccountUseCase.execute(employeeNumber, email)
        }
    }

    @Test
    fun `계정 확인 실패`() {
        // given
        given(queryUserPort.existsUserByEmployeeNumberAndEmail(employeeNumber, email))
            .willReturn(false)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            checkMatchedAccountUseCase.execute(employeeNumber, email)
        }
    }

}