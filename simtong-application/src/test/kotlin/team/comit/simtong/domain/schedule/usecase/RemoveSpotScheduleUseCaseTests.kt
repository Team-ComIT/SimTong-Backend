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
class RemoveSpotScheduleUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: ScheduleQueryUserPort

    @MockBean
    private lateinit var querySchedulePort: QuerySchedulePort

    @MockBean
    private lateinit var commandSchedulePort: CommandSchedulePort

    @MockBean
    private lateinit var securityPort: ScheduleSecurityPort

    private lateinit var removeSpotScheduleUseCase: RemoveSpotScheduleUseCase

    private val userId = UUID.randomUUID()

    private val spotId = UUID.randomUUID()

    private val scheduleId = UUID.randomUUID()

    private val scheduleStub: Schedule by lazy {
        Schedule(
            id = scheduleId,
            employeeId = userId,
            spotId = spotId,
            title = "test title",
            scope = Scope.ENTIRE,
            startAt = LocalDate.now(),
            endAt = LocalDate.now(),
            alarmTime = Schedule.DEFAULT_ALARM_TIME
        )
    }

    @BeforeEach
    fun setUp() {
        removeSpotScheduleUseCase = RemoveSpotScheduleUseCase(
            queryUserPort = queryUserPort,
            querySchedulePort = querySchedulePort,
            commandSchedulePort = commandSchedulePort,
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
            removeSpotScheduleUseCase.execute(scheduleId)
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

        given(querySchedulePort.queryScheduleById(scheduleId))
            .willReturn(scheduleStub)

        // when & then
        assertDoesNotThrow {
            removeSpotScheduleUseCase.execute(scheduleId)
        }
    }

    @Test
    fun `권한이 부족함`() {
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

        given(querySchedulePort.queryScheduleById(scheduleId))
            .willReturn(scheduleStub)

        // when & then
        assertThrows<UserExceptions.NotEnoughPermission> {
            removeSpotScheduleUseCase.execute(scheduleId)
        }
    }

    @Test
    fun `일정 범위가 다름`() {
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

        val scheduleStub = Schedule(
            id = scheduleId,
            employeeId = userId,
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
        assertThrows<ScheduleExceptions.DifferentScope> {
            removeSpotScheduleUseCase.execute(scheduleId)
        }
    }

    @Test
    fun `일정을 찾을 수 없음`() {
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
            .willReturn(null)

        // when & then
        assertThrows<ScheduleExceptions.NotFound> {
            removeSpotScheduleUseCase.execute(scheduleId)
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
            removeSpotScheduleUseCase.execute(scheduleId)
        }
    }
}