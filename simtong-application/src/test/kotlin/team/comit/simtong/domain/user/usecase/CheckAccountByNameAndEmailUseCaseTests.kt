package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.user.dto.CheckAccountByNameAndEmailRequest
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.spi.QueryUserPort

@ExtendWith(SpringExtension::class)
class CheckAccountByNameAndEmailUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    private lateinit var checkAccountByNameAndEmailUseCase: CheckAccountByNameAndEmailUseCase

    private val requestStub: CheckAccountByNameAndEmailRequest by lazy {
        CheckAccountByNameAndEmailRequest(
            name = "test name",
            email = "test@test.com"
        )
    }

    @BeforeEach
    fun setUp() {
        checkAccountByNameAndEmailUseCase = CheckAccountByNameAndEmailUseCase(queryUserPort)
    }

    @Test
    fun `계정 확인 성공`() {
        // given
        given(queryUserPort.existsUserByNameAndEmail(requestStub.name, requestStub.email))
            .willReturn(true)

        // when & then
        assertDoesNotThrow {
            checkAccountByNameAndEmailUseCase.execute(requestStub)
        }
    }

    @Test
    fun `계정 확인 실패`() {
        // given
        given(queryUserPort.existsUserByNameAndEmail(requestStub.name, requestStub.email))
            .willReturn(false)

        // when & then
        assertThrows<UserNotFoundException> {
            checkAccountByNameAndEmailUseCase.execute(requestStub)
        }
    }

}