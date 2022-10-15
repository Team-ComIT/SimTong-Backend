package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.auth.exception.UsedEmailException
import team.comit.simtong.domain.user.dto.CheckEmailDuplicationRequest
import team.comit.simtong.domain.user.spi.QueryUserPort

@ExtendWith(SpringExtension::class)
class CheckEmailDuplicationUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    private lateinit var checkEmailDuplicationUseCase: CheckEmailDuplicationUseCase

    private val requestStub: CheckEmailDuplicationRequest by lazy {
        CheckEmailDuplicationRequest(
            email = "test@test.com"
        )
    }

    @BeforeEach
    fun setUp() {
        checkEmailDuplicationUseCase = CheckEmailDuplicationUseCase(queryUserPort)
    }

    @Test
    fun `중복 없음`() {
        // given
        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        // when & then
        assertDoesNotThrow {
            checkEmailDuplicationUseCase.execute(requestStub)
        }
    }

    @Test
    fun `중복 있음`() {
        // given
        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(true)

        // when & then
        assertThrows<UsedEmailException> {
            checkEmailDuplicationUseCase.execute(requestStub)
        }
    }

}