package team.comit.simtong.domain.user.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.domain.auth.exception.UsedEmailException
import team.comit.simtong.domain.auth.model.AuthCodePolicy
import team.comit.simtong.domain.auth.spi.ReceiveTokenPort
import team.comit.simtong.domain.auth.usecase.dto.TokenResponse
import team.comit.simtong.domain.user.dto.DomainSignUpRequest
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.policy.SignUpPolicy
import team.comit.simtong.domain.auth.spi.DomainQueryAuthCodePolicyPort
import team.comit.simtong.domain.user.spi.DomainQueryUserPort
import team.comit.simtong.domain.user.spi.SaveUserPort
import team.comit.simtong.domain.user.spi.SecurityPort
import team.comit.simtong.domain.user.usecase.SignUpUseCase
import java.util.*

@ExtendWith(SpringExtension::class)
class SignUpUseCaseTests {

    @MockBean
    private lateinit var receiveTokenPort: ReceiveTokenPort

    @MockBean
    private lateinit var saveUserPort: SaveUserPort

    @MockBean
    private lateinit var securityPort: SecurityPort

    @MockBean
    private lateinit var domainQueryUserPort: DomainQueryUserPort

    @MockBean
    private lateinit var domainQueryAuthCodePolicyPort: DomainQueryAuthCodePolicyPort

    private lateinit var signUpPolicy: SignUpPolicy

    private lateinit var signUpUseCase: SignUpUseCase

    private val employeeNumber: Int = 1234567891

    private val name = "test name"

    private val email = "test@test.com"

    private val nickname = "test nickname"

    private val profileImagePath = "test path"

    private val saveUserStub: User by lazy {
        User(
            id = UUID.randomUUID(),
            name = name,
            nickname = nickname,
            email = email,
            password = "test encode password",
            employeeNumber = employeeNumber,
            authority = Authority.ROLE_COMMON,
            profileImagePath = profileImagePath
        )
    }

    private val userStub: User by lazy {
        User(
            nickname = nickname,
            name = name,
            email = email,
            password = "encode test password",
            employeeNumber = employeeNumber,
            authority = Authority.ROLE_COMMON,
            profileImagePath = profileImagePath
        )
    }

    private val authCodePolicyStub: AuthCodePolicy by lazy {
        AuthCodePolicy(
            key = email,
            expirationTime = 12345,
            attemptCount = 1,
            isVerified = true
        )
    }

    private val unVerifiedAuthCodePolicyStub: AuthCodePolicy by lazy {
        AuthCodePolicy(
            key = email,
            expirationTime = 12345,
            attemptCount = 5,
            isVerified = false
        )
    }

    private val requestStub: DomainSignUpRequest by lazy {
        DomainSignUpRequest(
            nickname = nickname,
            name = name,
            email = email,
            password = "test password",
            employeeNumber = employeeNumber,
            profileImagePath = profileImagePath
        )
    }

    private val responseStub: TokenResponse by lazy {
        TokenResponse(
            accessToken = "access token",
            accessTokenExp = Date(),
            refreshToken = "refresh token"
        )
    }

    @BeforeEach
    fun setUp() {
        signUpPolicy = SignUpPolicy(domainQueryAuthCodePolicyPort, domainQueryUserPort, securityPort)
        signUpUseCase = SignUpUseCase(receiveTokenPort, saveUserPort, signUpPolicy)
    }

    @Test
    fun `회원가입 성공`() {
        // given
        given(domainQueryAuthCodePolicyPort.queryAuthCodePolicyByEmail(requestStub.email))
            .willReturn(authCodePolicyStub)

        given(domainQueryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(securityPort.encode(requestStub.password))
            .willReturn(userStub.password)

        given(saveUserPort.saveUser(userStub))
            .willReturn(saveUserStub)

        given(receiveTokenPort.generateJsonWebToken(saveUserStub.id, saveUserStub.authority))
            .willReturn(responseStub)

        // when
        val result = signUpUseCase.execute(requestStub)

        // then
        assertThat(result).isEqualTo(responseStub)
    }

    @Test
    fun `인증되지 않은 이메일`() {
        // given
        given(domainQueryAuthCodePolicyPort.queryAuthCodePolicyByEmail(requestStub.email))
            .willReturn(unVerifiedAuthCodePolicyStub)

        // when & then
        assertThrows<UncertifiedEmailException> {
            signUpUseCase.execute(requestStub)
        }
    }

    @Test
    fun `이미 사용된 이메일`() {
        // given
        given(domainQueryAuthCodePolicyPort.queryAuthCodePolicyByEmail(requestStub.email))
            .willReturn(authCodePolicyStub)

        given(domainQueryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(true)

        // when & then
        assertThrows<UsedEmailException> {
            signUpUseCase.execute(requestStub)
        }
    }
}