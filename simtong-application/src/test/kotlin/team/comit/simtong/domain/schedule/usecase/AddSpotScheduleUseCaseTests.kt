package team.comit.simtong.domain.schedule.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.schedule.dto.AddSpotScheduleRequest
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.NotEnoughPermissionException
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import java.time.LocalDate
import java.util.UUID

@ExtendWith(SpringExtension::class)
class AddSpotScheduleUseCaseTests {

    @MockBean
    private lateinit var scheduleSecurityPort: ScheduleSecurityPort

    @MockBean
    private lateinit var commandSchedulePort: CommandSchedulePort

    @MockBean
    private lateinit var scheduleQueryUserPort: ScheduleQueryUserPort

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
            scheduleQueryUserPort, commandSchedulePort, scheduleSecurityPort
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

        given(scheduleSecurityPort.getCurrentUserId())
            .willReturn(userId)

        given(scheduleQueryUserPort.queryUserById(userId))
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

        given(scheduleSecurityPort.getCurrentUserId())
            .willReturn(userId)

        given(scheduleQueryUserPort.queryUserById(userId))
            .willReturn(userStub)

        // when & then
        assertThrows<NotEnoughPermissionException> {
            addSpotScheduleUseCase.execute(requestStub)
        }
    }

    @Test
    fun `유저를 찾을 수 없음`() {
        // given
        given(scheduleSecurityPort.getCurrentUserId())
            .willReturn(userId)

        given(scheduleQueryUserPort.queryUserById(userId))
            .willReturn(null)

        // when & then
        assertThrows<UserNotFoundException> {
            addSpotScheduleUseCase.execute(requestStub)
        }
    }

}