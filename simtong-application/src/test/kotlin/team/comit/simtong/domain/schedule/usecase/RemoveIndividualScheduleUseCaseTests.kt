package team.comit.simtong.domain.schedule.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.schedule.exception.ScheduleExceptions
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.CommandSchedulePort
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class RemoveIndividualScheduleUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: ScheduleQueryUserPort

    @MockBean
    private lateinit var querySchedulePort: QuerySchedulePort

    @MockBean
    private lateinit var commandSchedulePort: CommandSchedulePort

    @MockBean
    private lateinit var securityPort: ScheduleSecurityPort

    private lateinit var removeIndividualScheduleUseCase: RemoveIndividualScheduleUseCase

    private val userId = UUID.randomUUID()

    private val spotId = UUID.randomUUID()

    private val scheduleId = UUID.randomUUID()

    private val scheduleStub: Schedule by lazy {
        Schedule(
            id = scheduleId,
            employeeId = userId,
            spotId = spotId,
            title = "test title",
            scope = Scope.INDIVIDUAL,
            startAt = LocalDate.now(),
            endAt = LocalDate.now(),
            alarmTime = Schedule.DEFAULT_ALARM_TIME
        )
    }

    private val userStub by lazy {
        User(
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
    }

    @BeforeEach
    fun setUp() {
        removeIndividualScheduleUseCase = RemoveIndividualScheduleUseCase(
            querySchedulePort = querySchedulePort,
            commandSchedulePort = commandSchedulePort,
            queryUserPort = queryUserPort,
            securityPort = securityPort
        )
    }

    @Test
    fun `지점 일정 삭제 성공`() {
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

        given(querySchedulePort.queryScheduleById(scheduleId))
            .willReturn(scheduleStub)

        // when & then
        assertDoesNotThrow {
            removeIndividualScheduleUseCase.execute(scheduleId)
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
        assertThrows<UserExceptions.NotFound> {
            removeIndividualScheduleUseCase.execute(scheduleId)
        }
    }

    @Test
    fun `일정을 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(querySchedulePort.queryScheduleById(scheduleId))
            .willReturn(null)

        // when & then
        assertThrows<ScheduleExceptions.NotFound> {
            removeIndividualScheduleUseCase.execute(scheduleId)
        }
    }

    @Test
    fun `자신의 일정이 아님`() {
        // given
        val scheduleStub = Schedule(
            id = scheduleId,
            employeeId = UUID.randomUUID(),
            spotId = spotId,
            title = "test title",
            scope = Scope.INDIVIDUAL,
            startAt = LocalDate.now(),
            endAt = LocalDate.now(),
            alarmTime = Schedule.DEFAULT_ALARM_TIME
        )

        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(querySchedulePort.queryScheduleById(scheduleId))
            .willReturn(scheduleStub)

        // when & then
        assertThrows<ScheduleExceptions.NotScheduleOwner> {
            removeIndividualScheduleUseCase.execute(scheduleId)
        }
    }

    @Test
    fun `일정 범위가 다름`() {
        // given
        val scheduleStub = Schedule(
            id = scheduleId,
            employeeId = userId,
            spotId = spotId,
            title = "test title",
            scope = Scope.ENTIRE,
            startAt = LocalDate.now(),
            endAt = LocalDate.now(),
            alarmTime = Schedule.DEFAULT_ALARM_TIME
        )

        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(querySchedulePort.queryScheduleById(scheduleId))
            .willReturn(scheduleStub)

        // when & then
        assertThrows<ScheduleExceptions.DifferentScope> {
            removeIndividualScheduleUseCase.execute(scheduleId)
        }
    }
}