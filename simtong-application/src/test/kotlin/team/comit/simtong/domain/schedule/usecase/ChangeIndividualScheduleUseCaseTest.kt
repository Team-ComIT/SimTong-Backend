package team.comit.simtong.domain.schedule.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.schedule.dto.ChangeIndividualScheduleRequest
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
import java.time.LocalTime
import java.util.UUID

@SimtongTest
class ChangeIndividualScheduleUseCaseTest {

    @MockBean
    private lateinit var queryUserPort: ScheduleQueryUserPort

    @MockBean
    private lateinit var querySchedulePort: QuerySchedulePort

    @MockBean
    private lateinit var commandSchedulePort: CommandSchedulePort

    @Mock
    private lateinit var securityPort: ScheduleSecurityPort

    private lateinit var changeIndividualScheduleUseCase: ChangeIndividualScheduleUseCase

    @BeforeEach
    fun setUp() {
        changeIndividualScheduleUseCase = ChangeIndividualScheduleUseCase(
            queryUserPort = queryUserPort,
            querySchedulePort = querySchedulePort,
            commandSchedulePort = commandSchedulePort,
            securityPort = securityPort
        )
    }

    private val userId = UUID.randomUUID()

    private val spotId = UUID.randomUUID()

    private val scheduleId= UUID.randomUUID()

    private val userStub: User by lazy {
        User(
            id = userId,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = spotId,
            teamId = UUID.randomUUID(),
            profileImagePath = "test profile image"
        )
    }

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

    private val requestStub: ChangeIndividualScheduleRequest by lazy {
        ChangeIndividualScheduleRequest(
            scheduleId = scheduleId,
            title = "test title",
            startAt = LocalDate.now(),
            endAt = LocalDate.now(),
            alarm = LocalTime.now()
        )
    }

    @Test
    fun `개인 일정 변경 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(querySchedulePort.queryScheduleById(requestStub.scheduleId))
            .willReturn(scheduleStub)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        // when & then
        assertDoesNotThrow {
            changeIndividualScheduleUseCase.execute(requestStub)
        }
    }

    @Test
    fun `유저를 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(querySchedulePort.queryScheduleById(requestStub.scheduleId))
            .willReturn(scheduleStub)

        given(queryUserPort.queryUserById(userId))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            changeIndividualScheduleUseCase.execute(requestStub)
        }
    }

    @Test
    fun `일정을 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(querySchedulePort.queryScheduleById(requestStub.scheduleId))
            .willReturn(null)

        // when & then
        assertThrows<ScheduleExceptions.NotFound> {
            changeIndividualScheduleUseCase.execute(requestStub)
        }
    }
}