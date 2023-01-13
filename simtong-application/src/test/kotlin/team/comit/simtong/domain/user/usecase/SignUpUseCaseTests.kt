package team.comit.simtong.domain.user.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.auth.model.AuthCodeLimit
import team.comit.simtong.domain.file.exception.FileExceptions
import team.comit.simtong.domain.file.model.EmployeeCertificate
import team.comit.simtong.domain.spot.exception.SpotExceptions
import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.domain.team.exception.TeamExceptions
import team.comit.simtong.domain.team.model.Team
import team.comit.simtong.domain.user.dto.SignUpRequest
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandDeviceTokenPort
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserCommandAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserJwtPort
import team.comit.simtong.domain.user.spi.UserQueryAuthCodeLimitPort
import team.comit.simtong.domain.user.spi.UserQueryEmployeeCertificatePort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserQueryTeamPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.SimtongTest
import java.util.Date
import java.util.UUID

@SimtongTest
class SignUpUseCaseTests {

    @MockBean
    private lateinit var userJwtPort: UserJwtPort

    @MockBean
    private lateinit var commandUserPort: CommandUserPort

    @MockBean
    private lateinit var commandDeviceTokenPort: CommandDeviceTokenPort

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

    @MockBean
    private lateinit var commandAuthCodeLimitPort: UserCommandAuthCodeLimitPort

    @MockBean
    private lateinit var queryEmployeeCertificatePort: UserQueryEmployeeCertificatePort

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
        User.of(
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
            verified = true
        )
    }

    private val employeeCertificateStub: EmployeeCertificate by lazy {
        EmployeeCertificate(
            employeeNumber = employeeNumber,
            name = name,
            spotName = spotName,
            teamName = teamName
        )
    }

    private val requestStub: SignUpRequest by lazy {
        SignUpRequest(
            nickname = nickname,
            name = name,
            email = email,
            password = "test password",
            employeeNumber = employeeNumber,
            profileImagePath = profileImagePath,
            deviceToken = "test device token"
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
            commandDeviceTokenPort,
            queryUserPort,
            userQueryAuthCodeLimitPort,
            commandAuthCodeLimitPort,
            userQuerySpotPort,
            userQueryTeamPort,
            userSecurityPort,
            queryEmployeeCertificatePort
        )
    }

    @Test
    fun `회원가입 성공`() {
        // given
        val userStub = User.of(
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

        given(queryUserPort.existsUserByEmployeeNumber(requestStub.employeeNumber))
            .willReturn(false)

        given(queryUserPort.existsUserByNickname(nickname))
            .willReturn(false)

        given(queryEmployeeCertificatePort.queryEmployeeCertificateByNameAndEmployeeNumber(requestStub.name, requestStub.employeeNumber))
            .willReturn(employeeCertificateStub)

        given(userQuerySpotPort.querySpotByName(employeeCertificateStub.spotName))
            .willReturn(spotStub)

        given(userQueryTeamPort.queryTeamByName(employeeCertificateStub.teamName))
            .willReturn(teamStub)

        given(userSecurityPort.encode(requestStub.password))
            .willReturn(userStub.password.value)

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
            nickname = nickname,
            name = name,
            email = email,
            password = "test password",
            employeeNumber = employeeNumber,
            profileImagePath = null,
            deviceToken = "test device token"
        )

        val userStub = User.of(
            nickname = nickname,
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

        given(queryUserPort.existsUserByEmployeeNumber(requestStub.employeeNumber))
            .willReturn(false)

        given(queryUserPort.existsUserByNickname(nickname))
            .willReturn(false)

        given(queryEmployeeCertificatePort.queryEmployeeCertificateByNameAndEmployeeNumber(requestStub.name, requestStub.employeeNumber))
            .willReturn(employeeCertificateStub)

        given(userQuerySpotPort.querySpotByName(employeeCertificateStub.spotName))
            .willReturn(spotStub)

        given(userQueryTeamPort.queryTeamByName(employeeCertificateStub.teamName))
            .willReturn(teamStub)

        given(userSecurityPort.encode(requestStub.password))
            .willReturn(userStub.password.value)

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
            verified = false
        )

        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(unVerifiedAuthCodeLimitStub)

        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(queryUserPort.existsUserByEmployeeNumber(requestStub.employeeNumber))
            .willReturn(false)

        given(queryUserPort.existsUserByNickname(nickname))
            .willReturn(false)

        // when & then
        assertThrows<AuthExceptions.UncertifiedEmail> {
            signUpUseCase.execute(requestStub)
        }
    }

    @Test
    fun `인증되지 못한 이메일`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(null)

        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(queryUserPort.existsUserByEmployeeNumber(requestStub.employeeNumber))
            .willReturn(false)

        given(queryUserPort.existsUserByNickname(nickname))
            .willReturn(false)

        // when & then
        assertThrows<AuthExceptions.RequiredNewEmailAuthentication> {
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
        assertThrows<AuthExceptions.AlreadyUsedEmail> {
            signUpUseCase.execute(requestStub)
        }
    }

    @Test
    fun `사원 정보 찾기 실패`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(queryUserPort.existsUserByEmployeeNumber(requestStub.employeeNumber))
            .willReturn(false)

        given(queryUserPort.existsUserByNickname(nickname))
            .willReturn(false)

        given(queryEmployeeCertificatePort.queryEmployeeCertificateByNameAndEmployeeNumber(requestStub.name, requestStub.employeeNumber))
            .willReturn(null)

        // when & then
        assertThrows<FileExceptions.NotExistsEmployee> {
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

        given(queryUserPort.existsUserByEmployeeNumber(requestStub.employeeNumber))
            .willReturn(false)

        given(queryUserPort.existsUserByNickname(nickname))
            .willReturn(false)

        given(queryEmployeeCertificatePort.queryEmployeeCertificateByNameAndEmployeeNumber(requestStub.name, requestStub.employeeNumber))
            .willReturn(employeeCertificateStub)

        given(userQuerySpotPort.querySpotByName(employeeCertificateStub.spotName))
            .willReturn(null)

        // when & then
        assertThrows<SpotExceptions.NotFound> {
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

        given(queryUserPort.existsUserByEmployeeNumber(requestStub.employeeNumber))
            .willReturn(false)

        given(queryUserPort.existsUserByNickname(nickname))
            .willReturn(false)

        given(queryEmployeeCertificatePort.queryEmployeeCertificateByNameAndEmployeeNumber(requestStub.name, requestStub.employeeNumber))
            .willReturn(employeeCertificateStub)

        given(userQuerySpotPort.querySpotByName(employeeCertificateStub.spotName))
            .willReturn(spotStub)

        given(userQueryTeamPort.queryTeamByName(employeeCertificateStub.teamName))
            .willReturn(null)

        // when & then
        assertThrows<TeamExceptions.NotFound> {
            signUpUseCase.execute(requestStub)
        }
    }

    @Test
    fun `이미 사용중인 사원번호`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(queryUserPort.existsUserByEmployeeNumber(requestStub.employeeNumber))
            .willReturn(true)

        // when & then
        assertThrows<AuthExceptions.AlreadyUsedEmployeeNumber> {
            signUpUseCase.execute(requestStub)
        }
    }

    @Test
    fun `이미 사용중인 닉네임`() {
        // given
        given(userQueryAuthCodeLimitPort.queryAuthCodeLimitByEmail(requestStub.email))
            .willReturn(authCodeLimitStub)

        given(queryUserPort.existsUserByEmail(requestStub.email))
            .willReturn(false)

        given(queryUserPort.existsUserByEmployeeNumber(requestStub.employeeNumber))
            .willReturn(false)

        given(queryUserPort.existsUserByNickname(nickname))
            .willReturn(true)

        // when & then
        assertThrows<UserExceptions.AlreadyUsedNickname> {
            signUpUseCase.execute(requestStub)
        }
    }

}