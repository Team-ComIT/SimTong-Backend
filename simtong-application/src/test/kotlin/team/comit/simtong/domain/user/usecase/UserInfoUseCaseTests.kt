package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.DomainPropertiesInitialization
import team.comit.simtong.domain.spot.exception.SpotNotFoundException
import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import java.util.UUID

@Import(DomainPropertiesInitialization::class)
@ExtendWith(SpringExtension::class)
class UserInfoUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var userSecurityPort: UserSecurityPort

    @MockBean
    private lateinit var userQuerySpotPort: UserQuerySpotPort

    private lateinit var userInfoUseCase: UserInfoUseCase

    private val id = UUID.randomUUID()

    private val nickname = "test nickname"

    private val name = "test name"

    private val email = "test email"

    private val profileImagePath = "test path"

    private val userStub: User by lazy {
        User(
            id = id,
            nickname = nickname,
            name = name,
            email = email,
            password = "test password",
            employeeNumber = 1234567891,
            authority = Authority.ROLE_COMMON,
            spotId = id,
            teamId = id,
            profileImagePath = profileImagePath
        )
    }

    private val spotStub: Spot by lazy {
        Spot(
            id = id,
            name = name,
            location = "test location"
        )
    }

    @BeforeEach
    fun setUp() {
        userInfoUseCase = UserInfoUseCase(
            queryUserPort,
            userSecurityPort,
            userQuerySpotPort
        )
    }

    @Test
    fun `사용자 정보 보기`() {
        // given
        given(userSecurityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(userQuerySpotPort.querySpotById(id))
            .willReturn(spotStub)

        // when
        val result = userInfoUseCase.execute()

        // then
        assertEquals(result.name, name)
        assertEquals(result.email, email)
        assertEquals(result.nickName, nickname)
        assertEquals(result.spot, name)
        assertEquals(result.profileImagePath, profileImagePath)
    }

    @Test
    fun `유저 찾기 실패`() {
        // given
        given(userSecurityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(null)

        // when & then
        assertThrows<UserNotFoundException> {
            userInfoUseCase.execute()
        }
    }

    @Test
    fun `지점 찾기 실패`() {
        // given
        given(userSecurityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(userQuerySpotPort.querySpotById(id))
            .willReturn(null)

        // when & then
        assertThrows<SpotNotFoundException> {
            userInfoUseCase.execute()
        }
    }

}