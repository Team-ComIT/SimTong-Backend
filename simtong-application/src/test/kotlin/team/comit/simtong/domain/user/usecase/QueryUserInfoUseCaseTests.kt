package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.spot.exception.SpotExceptions
import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.domain.user.dto.QueryUserInfoResponse
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.SimtongTest
import java.util.UUID

@SimtongTest
class QueryUserInfoUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var securityPort: UserSecurityPort

    @MockBean
    private lateinit var querySpotPort: UserQuerySpotPort

    private lateinit var queryUserInfoUseCase: QueryUserInfoUseCase

    private val id: UUID = UUID.randomUUID()

    private val nickname = "test nickname"

    private val name = "test name"

    private val email = "test email"

    private val profileImagePath = "test path"

    private val userStub: User by lazy {
        User.of(
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

    private val responseStub: QueryUserInfoResponse by lazy {
        QueryUserInfoResponse(
            name = name,
            email = email,
            nickname = nickname,
            spot = spotStub.name,
            profileImagePath = profileImagePath
        )
    }

    @BeforeEach
    fun setUp() {
        queryUserInfoUseCase = QueryUserInfoUseCase(
            queryUserPort,
            securityPort,
            querySpotPort
        )
    }

    @Test
    fun `사용자 정보 보기`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(querySpotPort.querySpotById(id))
            .willReturn(spotStub)

        // when
        val response = queryUserInfoUseCase.execute()

        // then
        assertEquals(responseStub, response)
    }

    @Test
    fun `유저 찾기 실패`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            queryUserInfoUseCase.execute()
        }
    }

    @Test
    fun `지점 찾기 실패`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(querySpotPort.querySpotById(id))
            .willReturn(null)

        // when & then
        assertThrows<SpotExceptions.NotFound> {
            queryUserInfoUseCase.execute()
        }
    }

}