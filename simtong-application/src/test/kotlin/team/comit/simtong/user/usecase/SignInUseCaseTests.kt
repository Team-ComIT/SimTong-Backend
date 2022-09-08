package team.comit.simtong.user.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.auth.spi.ReceiveTokenPort
import team.comit.simtong.domain.auth.usecase.dto.TokenResponse
import team.comit.simtong.domain.user.exception.DifferentPasswordException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.SecurityPort
import team.comit.simtong.domain.user.usecase.SignInUseCase
import team.comit.simtong.domain.user.usecase.dto.SignInRequest
import java.util.*

@ExtendWith(SpringExtension::class)
class SignInUseCaseTests {


    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var securityPort: SecurityPort

    @MockBean
    private lateinit var tokenPort: ReceiveTokenPort

    private lateinit var signInUseCase: SignInUseCase

    private val employeeNumber: Int = 1234567891

    private val userStub: User by lazy {
        User(
            id = UUID.randomUUID(),
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "\$2a\$10\$.av8Mef703TEdRsGqoLcIe4esR6v0JfwpO9fBfH8bgZGrJYvQ0oBa",
            employeeNumber = employeeNumber,
            authority = Authority.ROLE_COMMON,
            profileImagePath = "test path"
        )
    }

    private val requestStub: SignInRequest by lazy {
        SignInRequest(
            employeeNumber = employeeNumber,
            password = "rlaqjawls1-"
        )
    }

    private val responseStub: TokenResponse by lazy {
        TokenResponse(
            accessToken = "test access token",
            accessTokenExp = Date(),
            refreshToken = "test refresh token"
        )
    }

    @BeforeEach
    fun setUp() {
        signInUseCase = SignInUseCase(queryUserPort, securityPort, tokenPort)
    }

    @Test
    fun `로그인 성공`() {
        // given
        given(queryUserPort.queryUserByEmployeeNumber(employeeNumber))
            .willReturn(userStub)

        given(securityPort.compare(requestStub.password, userStub.password))
            .willReturn(true)

        given(tokenPort.generateJsonWebToken(userStub.id, userStub.authority))
            .willReturn(responseStub)

        // when
        val response = signInUseCase.execute(requestStub)

        // then
        assertThat(response).isEqualTo(responseStub)
    }

    @Test
    fun `비밀번호 불일치`() {
        // given
        given(queryUserPort.queryUserByEmployeeNumber(employeeNumber))
            .willReturn(userStub)

        given(securityPort.compare(requestStub.password, userStub.password))
            .willReturn(false)

        // when then
        assertThrows<DifferentPasswordException> {
            signInUseCase.execute(requestStub)
        }
    }

}