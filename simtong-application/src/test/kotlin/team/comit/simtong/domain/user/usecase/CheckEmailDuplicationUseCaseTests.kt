package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.global.annotation.SimtongTest

@SimtongTest
class CheckEmailDuplicationUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    private lateinit var checkEmailDuplicationUseCase: CheckEmailDuplicationUseCase

    private val email = "test@test.com"

    @BeforeEach
    fun setUp() {
        checkEmailDuplicationUseCase = CheckEmailDuplicationUseCase(queryUserPort)
    }

    @Test
    fun `중복 없음`() {
        // given
        given(queryUserPort.existsUserByEmail(email))
            .willReturn(false)

        // when & then
        assertDoesNotThrow {
            checkEmailDuplicationUseCase.execute(email)
        }
    }

    @Test
    fun `중복 있음`() {
        // given
        given(queryUserPort.existsUserByEmail(email))
            .willReturn(true)

        // when & then
        assertThrows<AuthExceptions.AlreadyUsedEmail> {
            checkEmailDuplicationUseCase.execute(email)
        }
    }

}