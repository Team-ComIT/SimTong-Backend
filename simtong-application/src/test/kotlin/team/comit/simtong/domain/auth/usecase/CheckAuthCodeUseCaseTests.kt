package team.comit.simtong.domain.auth.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.auth.exception.AuthCodeNotFoundException
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

    @BeforeEach
    fun setUp() {
        checkAuthCodePolicy = CheckAuthCodePolicy(queryAuthCodePort)
        checkAuthCodeUseCase = CheckAuthCodeUseCase(checkAuthCodePolicy, commandAuthCodeLimitPort)
    }

    @Test
    fun `이메일 인증 확인 성공`() {
        // given
        given(queryAuthCodePort.existsAuthCodeByEmailAndCode(email, code))
            .willReturn(true)

        given(commandAuthCodeLimitPort.save(verifiedAuthCodeLimitStub))
            .willReturn(verifiedAuthCodeLimitStub)

        // when
        checkAuthCodeUseCase.execute(email, code)
    }

    @Test
    fun `이메일 인증 확인 실패`() {
        // given
        given(queryAuthCodePort.existsAuthCodeByEmailAndCode(email, code))
            .willReturn(false)

        // when & then
        assertThrows<AuthCodeNotFoundException> {
            checkAuthCodeUseCase.execute(email, code)
        }
    }

}