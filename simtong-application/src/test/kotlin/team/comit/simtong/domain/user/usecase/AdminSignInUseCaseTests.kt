package team.comit.simtong.domain.user.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.user.dto.SignInRequest
import team.comit.simtong.domain.user.exception.DifferentPasswordException
import team.comit.simtong.domain.user.exception.DifferentPermissionAccountException
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserJwtPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import java.util.*

@ExtendWith(SpringExtension::class)
class AdminSignInUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var userSecurityPort: UserSecurityPort

    @MockBean
    private lateinit var userJwtPort: UserJwtPort

    private lateinit var adminSignInUseCase: AdminSignInUseCase

    private val employeeNumber: Int = 1234567891

    private val adminStub: User by lazy {
        User(
            id = UUID.randomUUID(),
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = employeeNumber,
            authority = Authority.ROLE_ADMIN,
            spotId = UUID.randomUUID(),
            teamId = UUID.randomUUID(),
            profileImagePath = "test path"
        )
    }

    private val userStub: User by lazy {
        User(
            id = UUID.randomUUID(),
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = employeeNumber,
            authority = Authority.ROLE_COMMON,
            spotId = UUID.randomUUID(),
            teamId = UUID.randomUUID(),
            profileImagePath = "test path"
        )
    }

    private val requestStub: SignInRequest by lazy {
        SignInRequest(
            employeeNumber = employeeNumber,
            password = "test password"
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
        adminSignInUseCase = AdminSignInUseCase(
            queryUserPort,
            userJwtPort,
            userSecurityPort
        )
    }

    @Test
    fun `로그인 성공`() {
        // given
        given(queryUserPort.queryUserByEmployeeNumber(employeeNumber))
            .willReturn(adminStub)

        given(userSecurityPort.compare(requestStub.password, adminStub.password))
            .willReturn(true)

        given(userJwtPort.receiveToken(adminStub.id, adminStub.authority))
            .willReturn(responseStub)

        // when
        val response = adminSignInUseCase.execute(requestStub)

        // then
        assertThat(response).isEqualTo(responseStub)
    }

    @Test
    fun `비밀번호 불일치`() {
        // given
        given(queryUserPort.queryUserByEmployeeNumber(employeeNumber))
            .willReturn(adminStub)

        given(userSecurityPort.compare(requestStub.password, adminStub.password))
            .willReturn(false)

        // when & then
        assertThrows<DifferentPasswordException> {
            adminSignInUseCase.execute(requestStub)
        }
    }

    @Test
    fun `계정 찾기 실패`() {
        // given
        given(queryUserPort.queryUserByEmployeeNumber(employeeNumber))
            .willReturn(null)

        // when & then
        assertThrows<UserNotFoundException> {
            adminSignInUseCase.execute(requestStub)
        }
    }

    @Test
    fun `유저 계정`() {
        // given
        given(queryUserPort.queryUserByEmployeeNumber(employeeNumber))
            .willReturn(userStub)

        // when & then
        assertThrows<DifferentPermissionAccountException> {
            adminSignInUseCase.execute(requestStub)
        }
    }

}