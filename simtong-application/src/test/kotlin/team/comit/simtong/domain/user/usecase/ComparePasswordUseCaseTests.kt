package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.value.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.SimtongTest
import java.util.UUID

@SimtongTest
class ComparePasswordUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var securityPort: UserSecurityPort

    private lateinit var comparePasswordUseCase: ComparePasswordUseCase

    private val userId: UUID = UUID.randomUUID()

    private val passwordStub: String = "test password"

    private val userStub: User by lazy {
        User(
            id = userId,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = UUID.randomUUID(),
            teamId = UUID.randomUUID(),
            profileImagePath = User.DEFAULT_IMAGE
        )
    }

    @BeforeEach
    fun setUp() {
        comparePasswordUseCase = ComparePasswordUseCase(
            queryUserPort = queryUserPort,
            securityPort = securityPort
        )
    }

    @Test
    fun `비밀번호가 일치함`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(securityPort.compare(passwordStub, userStub.password))
            .willReturn(true)

        // when & then
        assertDoesNotThrow {
            comparePasswordUseCase.execute(passwordStub)
        }
    }

    @Test
    fun `비밀번호가 일치하지 않음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(securityPort.compare(passwordStub, userStub.password))
            .willReturn(false)

        // when & then
        assertThrows<UserExceptions.DifferentPassword> {
            comparePasswordUseCase.execute(passwordStub)
        }
    }

    @Test
    fun `유저를 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(null)

        assertThrows<UserExceptions.NotFound> {
            comparePasswordUseCase.execute(passwordStub)
        }
    }
}