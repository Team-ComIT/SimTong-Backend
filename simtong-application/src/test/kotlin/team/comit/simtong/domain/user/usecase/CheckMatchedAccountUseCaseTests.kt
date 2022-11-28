package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.user.dto.CheckMatchedAccountRequest
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.global.annotation.SimtongTest

@SimtongTest
class CheckMatchedAccountUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    private lateinit var checkMatchedAccountUseCase: CheckMatchedAccountUseCase

    private val requestStub: CheckMatchedAccountRequest by lazy {
        CheckMatchedAccountRequest(
            employeeNumber = 1234567890,
            email = "test@test.com"
        )
    }

    @BeforeEach
    fun setUp() {
        checkMatchedAccountUseCase = CheckMatchedAccountUseCase(queryUserPort)
    }

    @Test
    fun `계정 확인 성공`() {
        // given
        given(queryUserPort.existsUserByEmployeeNumberAndEmail(requestStub.employeeNumber, requestStub.email))
            .willReturn(true)

        // when & then
        assertDoesNotThrow {
            checkMatchedAccountUseCase.execute(requestStub)
        }
    }

    @Test
    fun `계정 확인 실패`() {
        // given
        given(queryUserPort.existsUserByEmployeeNumberAndEmail(requestStub.employeeNumber, requestStub.email))
            .willReturn(false)

        // when & then
        assertThrows<UserNotFoundException> {
            checkMatchedAccountUseCase.execute(requestStub)
        }
    }

}