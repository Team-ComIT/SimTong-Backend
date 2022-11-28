package team.comit.simtong.domain.schedule.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.schedule.dto.AddSpotScheduleRequest
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.NotEnoughPermissionException
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class AddSpotScheduleUseCaseTests {

    @MockBean
    private lateinit var securityPort: ScheduleSecurityPort

    @MockBean
    private lateinit var commandSchedulePort: CommandSchedulePort

    @MockBean
    private lateinit var queryUserPort: ScheduleQueryUserPort

    private lateinit var addSpotScheduleUseCase: AddSpotScheduleUseCase

    private val userId: UUID = UUID.randomUUID()

    private val spotId: UUID = UUID.randomUUID()

    private val requestStub : AddSpotScheduleRequest by lazy {
        AddSpotScheduleRequest(
            spotId = spotId,
            title = "test title",
            startAt = LocalDate.now(),
            endAt = LocalDate.now()
        )
    }

    @BeforeEach
    fun setUp() {
        addSpotScheduleUseCase = AddSpotScheduleUseCase(
            queryUserPort, commandSchedulePort, securityPort
        )
    }

    @Test
    fun `지점 일정 추가 성공`() {
        // given
        val userStub = User(
            id = userId,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_ADMIN,
            spotId = spotId,
            teamId = UUID.randomUUID(),
            profileImagePath = "test profile image"
        )

        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        // when & then
        assertDoesNotThrow {
            addSpotScheduleUseCase.execute(requestStub)
        }
    }

    @Test
    fun `지점 변경 권한 부족`() {
        // given
        val userStub = User(
            id = userId,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_ADMIN,
            spotId = UUID.randomUUID(),
            teamId = UUID.randomUUID(),
            profileImagePath = "test profile image"
        )

        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        // when & then
        assertThrows<NotEnoughPermissionException> {
            addSpotScheduleUseCase.execute(requestStub)
        }
    }

    @Test
    fun `최고 관리자 계정`() {
        // given
        val userStub = User(
            id = userId,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_SUPER,
            spotId = UUID.randomUUID(),
            teamId = UUID.randomUUID(),
            profileImagePath = "test profile image"
        )

        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        // when & then
        assertDoesNotThrow {
            addSpotScheduleUseCase.execute(requestStub)
        }
    }

    @Test
    fun `유저를 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(null)

        // when & then
        assertThrows<UserNotFoundException> {
            addSpotScheduleUseCase.execute(requestStub)
        }
    }

}