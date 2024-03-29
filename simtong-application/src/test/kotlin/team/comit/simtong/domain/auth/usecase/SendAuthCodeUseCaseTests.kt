package team.comit.simtong.domain.auth.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.willDoNothing
import org.mockito.kotlin.any
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.auth.model.AuthCode
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.auth.spi.CommandAuthCodeLimitPort
import team.comit.simtong.domain.auth.spi.CommandAuthCodePort
import team.comit.simtong.domain.auth.spi.QueryAuthCodeLimitPort
import team.comit.simtong.domain.auth.spi.SendEmailPort
import team.comit.simtong.global.annotation.SimtongTest

@SimtongTest
class SendAuthCodeUseCaseTests {

    @MockBean
    private lateinit var queryAuthCodeLimitPort: QueryAuthCodeLimitPort

    @MockBean
    private lateinit var commandAuthCodeLimitPort: CommandAuthCodeLimitPort

    @MockBean
    private lateinit var commandAuthCodePort: CommandAuthCodePort

    @MockBean
    private lateinit var sendEmailPort: SendEmailPort

    private lateinit var sendAuthCodeUseCase: SendAuthCodeUseCase

    private val email = "test@test.com"

    private val code = "123456"

    private val verifiedAuthCodeLimitStub by lazy {
        AuthCodeLimit(
            key = email,
            expirationTime = AuthCodeLimit.EXPIRED,
            attemptCount = 1,
            verified = true
        )
    }

    private val exceedAuthCodeLimitStub by lazy {
        AuthCodeLimit(
            key = email,
            expirationTime = AuthCodeLimit.EXPIRED,
            attemptCount = AuthCodeLimit.MAX_ATTEMPT_COUNT,
            verified = false
        )
    }

    private val authCodeStub by lazy {
        AuthCode(
            key = email,
            code = code,
            expirationTime = AuthCode.EXPIRED
        )
    }

    @BeforeEach
    fun setUp() {
        sendAuthCodeUseCase = SendAuthCodeUseCase(
            commandAuthCodeLimitPort,
            commandAuthCodePort,
            queryAuthCodeLimitPort,
            sendEmailPort
        )
    }

    @Test
    fun `이메일 인증 코드 발송`() {
        // given
        given(queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(email))
            .willReturn(null)

        given(commandAuthCodePort.save(any()))
            .willReturn(authCodeStub)

        willDoNothing().given(sendEmailPort).sendAuthCode(code, email)

        // when & then
        assertDoesNotThrow {
            sendAuthCodeUseCase.execute(email)
        }
    }

    @Test
    fun `이미 인증된 이메일`() {
        // given
        given(queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(email))
            .willReturn(verifiedAuthCodeLimitStub)

        // when & then
        assertThrows<AuthExceptions.AlreadyCertifiedEmail> {
            sendAuthCodeUseCase.execute(email)
        }
    }

    @Test
    fun `이메일 인증 코드 전송 횟수 초과`() {
        // given
        given(queryAuthCodeLimitPort.queryAuthCodeLimitByEmail(email))
            .willReturn(exceedAuthCodeLimitStub)

        // when & then
        assertThrows<AuthExceptions.ExceededSendAuthCodeRequest> {
            sendAuthCodeUseCase.execute(email)
        }
    }

}