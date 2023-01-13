package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.auth.spi.QueryAuthCodeLimitPort
import team.comit.simtong.domain.user.dto.request.ChangeEmailData
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.SimtongTest
import java.util.UUID

@SimtongTest
class ChangeEmailUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var securityPort: UserSecurityPort

    @MockBean
    private lateinit var queryAuthCodeLimitPort: QueryAuthCodeLimitPort

    @MockBean
    private lateinit var commandUserPort: CommandUserPort

    private lateinit var changeEmailUseCase: ChangeEmailUseCase

    private val id = UUID.randomUUID()

    private val requestStub: ChangeEmailData by lazy {
        ChangeEmailData(
            email = "test@test.com"
        )
    }

    private val authCodeLimitStub: AuthCodeLimit by lazy {
        AuthCodeLimit.certified(
            email = "test@test.com"
        )
    }

    private val uncertifiedAuthCodeLimit: AuthCodeLimit by lazy {
        AuthCodeLimit.issue(
            email = "test@test.com"
        )
    }

    private val userStub: User by lazy {
        User.of(
            id = id,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = id,
            teamId = id,
            profileImagePath = "test profile"
        )
    }

    @BeforeEach
    fun setUp() {
        changeEmailUseCase = ChangeEmailUseCase(
            queryUserPort,
            securityPort,
            queryAuthCodeLimitPort,
            commandUserPort
        )
    }

    @Test
    fun `이메일 변경 성공`() {
        // given
        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        // when & then
        assertDoesNotThrow {
            changeEmailUseCase.execute(requestStub)
        }
    }

    @Test
    fun `중복된 이메일`() {
        // given
        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(true)

        // when & then
        assertThrows<AuthExceptions.AlreadyUsedEmail> {
            changeEmailUseCase.execute(requestStub)
        }
    }

    @Test
    fun `인증하지 않은 이메일`() {
        // given
        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(null)

        // when & then
        assertThrows<AuthExceptions.RequiredNewEmailAuthentication> {
            changeEmailUseCase.execute(requestStub)
        }
    }

    @Test
    fun `인증되지 않은 이메일`() {
        // given
        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(uncertifiedAuthCodeLimit)

        // when & then
        assertThrows<AuthExceptions.UncertifiedEmail> {
            changeEmailUseCase.execute(requestStub)
        }
    }

    @Test
    fun `찾을 수 없는 사용자`() {
        // given
        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            changeEmailUseCase.execute(requestStub)
        }
    }


}