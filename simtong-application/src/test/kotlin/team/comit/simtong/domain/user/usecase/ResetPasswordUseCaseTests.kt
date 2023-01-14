package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.user.dto.request.ResetPasswordData
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserCommandAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserQueryAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.SimtongTest
import java.util.UUID

@SimtongTest
class ResetPasswordUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var userQueryAuthCodeLimitPort: UserQueryAuthCodeLimitPort

    @MockBean
    private lateinit var commandAuthCodeLimitPort: UserCommandAuthCodeLimitPort

    @MockBean
    private lateinit var commandUserPort: CommandUserPort

    @MockBean
    private lateinit var securityPort: UserSecurityPort

    private lateinit var resetPasswordUseCase: ResetPasswordUseCase

    private val email = "test@test.com"

    private val id = UUID.randomUUID()

    private val requestStub: ResetPasswordData by lazy {
        ResetPasswordData(
            email = email,
            employeeNumber = 1234567890,
            newPassword = "test password"
        )
    }

    private val authCodeLimitStub: AuthCodeLimit by lazy {
        AuthCodeLimit.certified(email)
    }

    private val uncertifiedAuthCodeLimit: AuthCodeLimit by lazy {
        AuthCodeLimit.issue(
            email = email
        )
    }

    private val userStub: User by lazy {
        User.of(
            id = id,
            nickname = "test nickname",
            name = "test name",
            email = email,
            password = "password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = id,
            teamId = id,
            profileImagePath = "test profile"
        )
    }

    @BeforeEach
    fun setUp() {
        resetPasswordUseCase = ResetPasswordUseCase(
            queryUserPort,
            userQueryAuthCodeLimitPort,
            commandAuthCodeLimitPort,
            commandUserPort,
            securityPort
        )
    }

    @Test
    fun `비밀번호 초기화 성공`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(queryUserPort.queryUserByEmailAndEmployeeNumber(requestStub.email, requestStub.employeeNumber))
            .willReturn(userStub)

        given(securityPort.encode(requestStub.newPassword))
            .willReturn(requestStub.newPassword)

        // when & then
        assertDoesNotThrow {
            resetPasswordUseCase.execute(requestStub)
        }
    }

    @Test
    fun `인증하지 않은 이메일`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(null)

        // when & then
        assertThrows<AuthExceptions.RequiredNewEmailAuthentication> {
            resetPasswordUseCase.execute(requestStub)
        }
    }

    @Test
    fun `인증되지 않은 이메일`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(uncertifiedAuthCodeLimit)

        // when & then
        assertThrows<AuthExceptions.UncertifiedEmail> {
            resetPasswordUseCase.execute(requestStub)
        }
    }

    @Test
    fun `찾을 수 없는 사용자`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(queryUserPort.queryUserByEmailAndEmployeeNumber(requestStub.email, requestStub.employeeNumber))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            resetPasswordUseCase.execute(requestStub)
        }
    }

}