package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.schedule.exception.ScheduleExceptions
import team.comit.simtong.domain.spot.exception.SpotExceptions
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserQuerySpotPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.DomainPropertiesInitialization
import java.util.UUID

@Import(DomainPropertiesInitialization::class)
@ExtendWith(SpringExtension::class)
class ChangeSpotUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var commandUserPort: CommandUserPort

    @MockBean
    private lateinit var querySpotPort: UserQuerySpotPort

    @MockBean
    private lateinit var userSecurityPort: UserSecurityPort

    private lateinit var changeSpotUseCase: ChangeSpotUseCase

    private val userId = UUID.randomUUID()
    private val spotId = UUID.randomUUID()

    private val userStub: User by lazy {
        User(
            id = userId,
            nickname = "test nickname",
            name = "test name",
            email = "test email",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = spotId,
            teamId = UUID.randomUUID(),
            profileImagePath = "test path"
        )
    }

    @BeforeEach
    fun setUp() {
        changeSpotUseCase = ChangeSpotUseCase(queryUserPort, commandUserPort, querySpotPort, userSecurityPort)
    }

    @Test
    fun `지점 변경 성공`() {
        // given
        given(userSecurityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(querySpotPort.existsSpotById(spotId))
            .willReturn(true)

        // when & then
        assertDoesNotThrow {
            changeSpotUseCase.execute(spotId)
        }
    }

    @Test
    fun `유저를 찾을 수 없음`() {
        // given
        given(userSecurityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            changeSpotUseCase.execute(spotId)
        }
    }

    @Test
    fun `지점을 찾을 수 없음`() {
        // given
        given(userSecurityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(querySpotPort.existsSpotById(spotId))
            .willReturn(false)

        // when & then
        assertThrows<SpotExceptions.NotFound> {
            changeSpotUseCase.execute(spotId)
        }
    }

}