package team.comit.simtong.domain.auth.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.auth.exception.AuthCodeMismatchException
import team.comit.simtong.domain.auth.model.AuthCode
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.auth.policy.CheckAuthCodePolicy
import team.comit.simtong.domain.auth.spi.CommandAuthCodeLimitPort
import team.comit.simtong.domain.auth.spi.QueryAuthCodePort

@ExtendWith(SpringExtension::class)
class CheckAuthCodeUseCaseTests {

    @MockBean
    private lateinit var queryAuthCodePort: QueryAuthCodePort

    @MockBean
    private lateinit var commandAuthCodeLimitPort: CommandAuthCodeLimitPort

    private lateinit var checkAuthCodePolicy: CheckAuthCodePolicy

    private lateinit var checkAuthCodeUseCase: CheckAuthCodeUseCase

    private val email = "test@test.com"

    private val code = "123456"

    private val verifiedAuthCodeLimitStub: AuthCodeLimit by lazy {
        AuthCodeLimit(
            key = email,
            expirationTime = AuthCodeLimit.VERIFIED_EXPIRED,
            attemptCount = 0,
            isVerified = true
        )
    }

    private val authCodeStub: AuthCode by lazy {
        AuthCode(
            key = email,
            code = code,
            expirationTime = AuthCode.EXPIRED
        )
    }

    private val differentAuthCodeStub by lazy {
        AuthCode(
            key = email,
            code = "654321",
            expirationTime = AuthCode.EXPIRED
        )
    }

    @BeforeEach
    fun setUp() {
        checkAuthCodePolicy = CheckAuthCodePolicy(queryAuthCodePort)
        checkAuthCodeUseCase = CheckAuthCodeUseCase(checkAuthCodePolicy, commandAuthCodeLimitPort)
    }

    @Test
    fun `이메일 인증 확인 성공`() {
        // given
        given(queryAuthCodePort.queryAuthCodeByEmail(email))
            .willReturn(authCodeStub)

        given(commandAuthCodeLimitPort.save(verifiedAuthCodeLimitStub))
            .willReturn(verifiedAuthCodeLimitStub)

        // when
        checkAuthCodeUseCase.execute(email, code)
    }

    @Test
    fun `이메일 인증 코드 찾지 못함`() {
        // given
        given(queryAuthCodePort.queryAuthCodeByEmail(email))
            .willReturn(null)

        // when & then
        assertThrows<AuthCodeMismatchException> {
            checkAuthCodeUseCase.execute(email, code)
        }
    }

    @Test
    fun `이메일 인증 코드 비 일치`() {
        // given
        given(queryAuthCodePort.queryAuthCodeByEmail(email))
            .willReturn(differentAuthCodeStub)

        // when & then
        assertThrows<AuthCodeMismatchException> {
            checkAuthCodeUseCase.execute(email, code)
        }
    }

}