package team.comit.simtong.domain.auth.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.auth.dto.request.CheckAuthCodeData
import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.auth.model.AuthCode
import team.comit.simtong.domain.auth.model.Code
import team.comit.simtong.domain.auth.spi.CommandAuthCodeLimitPort
import team.comit.simtong.domain.auth.spi.QueryAuthCodePort
import team.comit.simtong.global.annotation.SimtongTest

@SimtongTest
class CheckAuthCodeUseCaseTests {

    @MockBean
    private lateinit var queryAuthCodePort: QueryAuthCodePort

    @MockBean
    private lateinit var commandAuthCodeLimitPort: CommandAuthCodeLimitPort

    private lateinit var checkAuthCodeUseCase: CheckAuthCodeUseCase

    private val email = "test@test.com"

    private val code = "123456"

    private val authCodeStub: AuthCode by lazy {
        AuthCode(
            key = email,
            code = Code.of(code),
            expirationTime = AuthCode.EXPIRED
        )
    }

    private val differentAuthCodeStub: AuthCode by lazy {
        AuthCode(
            key = email,
            code = Code.of("654321"),
            expirationTime = AuthCode.EXPIRED
        )
    }

    private val requestStub = CheckAuthCodeData(email, code)

    @BeforeEach
    fun setUp() {
        checkAuthCodeUseCase = CheckAuthCodeUseCase(
            commandAuthCodeLimitPort,
            queryAuthCodePort
        )
    }

    @Test
    fun `이메일 인증 확인 성공`() {
        // given
        given(queryAuthCodePort.queryAuthCodeByEmail(email))
            .willReturn(authCodeStub)

        // when & then
        assertDoesNotThrow {
            checkAuthCodeUseCase.execute(requestStub)
        }
    }

    @Test
    fun `이메일 인증 코드 찾지 못함`() {
        // given
        given(queryAuthCodePort.queryAuthCodeByEmail(email))
            .willReturn(null)

        // when & then
        assertThrows<AuthExceptions.RequiredNewEmailAuthentication> {
            checkAuthCodeUseCase.execute(requestStub)
        }
    }

    @Test
    fun `이메일 인증 코드 불 일치`() {
        // given
        given(queryAuthCodePort.queryAuthCodeByEmail(email))
            .willReturn(differentAuthCodeStub)

        // when & then
        assertThrows<AuthExceptions.DifferentAuthCode> {
            checkAuthCodeUseCase.execute(requestStub)
        }
    }

}