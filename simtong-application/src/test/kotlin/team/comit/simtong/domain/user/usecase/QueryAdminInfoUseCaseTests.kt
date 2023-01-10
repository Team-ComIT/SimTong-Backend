package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.spot.exception.SpotExceptions
import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.domain.user.dto.QueryAdminInfoResponse
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.value.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.SimtongTest
import java.util.UUID

@SimtongTest
class QueryAdminInfoUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var querySpotPort: UserQuerySpotPort

    @MockBean
    private lateinit var securityPort: UserSecurityPort

    private lateinit var queryAdminInfoUseCase: QueryAdminInfoUseCase

    private val id: UUID = UUID.randomUUID()

    private val spotId: UUID = UUID.randomUUID()

    private val name = "test name"

    private val spotName = "test spot name"

    private val nickname = "test nickname"

    private val email = "test@test.com"

    private val profileImagePath = "test profile image"

    private val userStub: User by lazy {
        User(
            id = id,
            nickname = nickname,
            name = name,
            email = email,
            password = "test password",
            employeeNumber = 1234567891,
            authority = Authority.ROLE_ADMIN,
            spotId = spotId,
            teamId = id,
            profileImagePath = profileImagePath
        )
    }

    private val spotStub: Spot by lazy {
        Spot(
            id = spotId,
            name = spotName,
            location = "test location"
        )
    }

    private val responseStub: QueryAdminInfoResponse by lazy {
        QueryAdminInfoResponse(
            name = name,
            email = email,
            nickname = nickname,
            spot = QueryAdminInfoResponse.SpotResponse(
                id = spotId,
                name = spotName
            ),
            profileImagePath = profileImagePath
        )
    }

    @BeforeEach
    fun setUp() {
        queryAdminInfoUseCase = QueryAdminInfoUseCase(
            queryUserPort,
            querySpotPort,
            securityPort
        )
    }

    @Test
    fun `관리자 정보 보기`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(querySpotPort.querySpotById(userStub.spotId))
            .willReturn(spotStub)

        // when
        val response = queryAdminInfoUseCase.execute()

        // then
        assertEquals(response, responseStub)
    }

    @Test
    fun `관리자 찾기 실패`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            queryAdminInfoUseCase.execute()
        }
    }

    @Test
    fun `지점 찾기 실패`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(querySpotPort.querySpotById(userStub.spotId))
            .willReturn(null)

        // when & then
        assertThrows<SpotExceptions.NotFound> {
            queryAdminInfoUseCase.execute()
        }
    }
}