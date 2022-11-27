package team.comit.simtong.domain.user.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.DomainPropertiesInitialization
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.auth.exception.RequiredNewEmailAuthenticationException
import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.domain.auth.exception.UsedEmailException
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.spot.exception.SpotNotFoundException
import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.domain.team.exception.TeamNotFoundException
import team.comit.simtong.domain.team.model.Team
import team.comit.simtong.domain.user.dto.SignUpRequest
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserJwtPort
import team.comit.simtong.domain.user.spi.UserQueryAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserQueryTeamPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import java.util.*

@Import(DomainPropertiesInitialization::class)
@ExtendWith(SpringExtension::class)
class SignUpUseCaseTests {

    @MockBean
    private lateinit var userJwtPort: UserJwtPort

    @MockBean
    private lateinit var commandUserPort: CommandUserPort

    @MockBean
    private lateinit var userSecurityPort: UserSecurityPort

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var userQuerySpotPort: UserQuerySpotPort

    @MockBean
    private lateinit var userQueryTeamPort: UserQueryTeamPort

    @MockBean
    private lateinit var userQueryAuthCodeLimitPort: UserQueryAuthCodeLimitPort

    private lateinit var signUpUseCase: SignUpUseCase

    private val employeeNumber: Int = 1234567891

    private val name = "test name"

    private val email = "test@test.com"

    private val nickname = "test nickname"

    private val profileImagePath = "test path"

    private val spotName = "test spotName"

    private val teamName = "test teamName"

    private val spotStub: Spot by lazy {
        Spot(
            id = UUID.randomUUID(),
            name = spotName,
            location = "test location"
        )
    }

    private val teamStub: Team by lazy {
        Team(
            id = UUID.randomUUID(),
            name = teamName,
            spotId = spotStub.id
        )
    }

    private val saveUserStub: User by lazy {
        User(
            id = UUID.randomUUID(),
            name = name,
            nickname = nickname,
            email = email,
            password = "test encode password",
            employeeNumber = employeeNumber,
            authority = Authority.ROLE_COMMON,
            spotId = spotStub.id,
            teamId = teamStub.id,
            profileImagePath = profileImagePath
        )
    }

    private val authCodeLimitStub: AuthCodeLimit by lazy {
        AuthCodeLimit(
            key = email,
            expirationTime = 12345,
            attemptCount = 1,
            isVerified = true
        )
    }

    private val requestStub: SignUpRequest by lazy {
        SignUpRequest(
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
        signUpUseCase = SignUpUseCase(
            userJwtPort,
            commandUserPort,
            queryUserPort,
            userQueryAuthCodeLimitPort,
            userQuerySpotPort,
            userQueryTeamPort,
            userSecurityPort
        )
    }

    @Test
    fun `회원가입 성공`() {
        // given
        val userStub = User(
            nickname = nickname,
            name = name,
            email = email,
            password = "encode test password",
            employeeNumber = employeeNumber,
            authority = Authority.ROLE_COMMON,
            spotId = spotStub.id,
            teamId = teamStub.id,
            profileImagePath = profileImagePath
        )

        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(userSecurityPort.encode(requestStub.password))
            .willReturn(userStub.password)

        given(userQuerySpotPort.querySpotByName(spotName))
            .willReturn(spotStub)

        given(userQueryTeamPort.queryTeamByName(teamName))
            .willReturn(teamStub)

        given(commandUserPort.save(userStub))
            .willReturn(saveUserStub)

        given(userJwtPort.receiveToken(saveUserStub.id, saveUserStub.authority))
            .willReturn(responseStub)

        // when
        val result = signUpUseCase.execute(requestStub)

        // then
        assertThat(result).isEqualTo(responseStub)
    }

    @Test
    fun `회원가입 성공 OPTINAL`() {
        // given
        val requestStub = SignUpRequest(
            nickname = null,
            name = name,
            email = email,
            password = "test password",
            employeeNumber = employeeNumber,
            profileImagePath = null
        )

        val userStub = User(
            nickname = "",
            name = name,
            email = email,
            password = "encode test password",
            employeeNumber = employeeNumber,
            authority = Authority.ROLE_COMMON,
            spotId = spotStub.id,
            teamId = teamStub.id,
            profileImagePath = User.DEFAULT_IMAGE
        )

        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(userSecurityPort.encode(requestStub.password))
            .willReturn(userStub.password)

        given(userQuerySpotPort.querySpotByName(spotName))
            .willReturn(spotStub)

        given(userQueryTeamPort.queryTeamByName(teamName))
            .willReturn(teamStub)

        given(commandUserPort.save(userStub))
            .willReturn(saveUserStub)

        given(userJwtPort.receiveToken(saveUserStub.id, saveUserStub.authority))
            .willReturn(responseStub)

        // when
        val result = signUpUseCase.execute(requestStub)

        // then
        assertThat(result).isEqualTo(responseStub)
    }

    @Test
    fun `인증되지 않은 이메일`() {
        // given
        val unVerifiedAuthCodeLimitStub = AuthCodeLimit(
            key = email,
            expirationTime = 12345,
            attemptCount = 5,
            isVerified = false
        )

        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(unVerifiedAuthCodeLimitStub)

        // when & then
        assertThrows<UncertifiedEmailException> {
            signUpUseCase.execute(requestStub)
        }
    }

    @Test
    fun `인증되지 못한 이메일`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(null)

        // when & then
        assertThrows<RequiredNewEmailAuthenticationException> {
            signUpUseCase.execute(requestStub)
        }
    }

    @Test
    fun `이미 사용된 이메일`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(true)

        // when & then
        assertThrows<UsedEmailException> {
            signUpUseCase.execute(requestStub)
        }
    }

    @Test
    fun `지점 찾기 실패`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(userQuerySpotPort.querySpotByName(spotName))
            .willReturn(null)

        // when & then
        assertThrows<SpotNotFoundException> {
            signUpUseCase.execute(requestStub)
        }
    }

    @Test
    fun `팀 찾기 실패`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(userQuerySpotPort.querySpotByName(spotName))
            .willReturn(spotStub)

        given(userQueryTeamPort.queryTeamByName(teamName))
            .willReturn(null)

        // when & then
        assertThrows<TeamNotFoundException> {
            signUpUseCase.execute(requestStub)
        }
    }

}