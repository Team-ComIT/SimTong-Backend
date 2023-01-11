package team.comit.simtong.domain.schedule.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.schedule.dto.response.QueryIndividualSpotScheduleResponse
import team.comit.simtong.domain.schedule.dto.response.ScheduleResponse
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.model.value.Scope
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.model.value.Authority
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class QueryIndividualSpotScheduleUseCaseTests {

    @MockBean
    private lateinit var querySchedulePort: QuerySchedulePort

    @MockBean
    private lateinit var queryUserPort: ScheduleQueryUserPort

    @MockBean
    private lateinit var securityPort: ScheduleSecurityPort

    private lateinit var queryIndividualSpotScheduleUseCase: QueryIndividualSpotScheduleUseCase

    private val date: LocalDate = LocalDate.now()

    private val minDate: LocalDate = LocalDate.MIN

    private val maxDate: LocalDate = LocalDate.MAX

    private val userId: UUID = UUID.randomUUID()

    private val spotId: UUID = UUID.randomUUID()

    private val scheduleId: UUID = UUID.randomUUID()

    private val userStub: User by lazy {
        User(
            id = userId,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = UUID.randomUUID(),
            teamId = UUID.randomUUID(),
            profileImagePath = "test profile image"
        )
    }

    private val individualScheduleStub: Schedule by lazy {
        Schedule(
            id = scheduleId,
            employeeId = userId,
            spotId = spotId,
            title = "test title",
            scope = Scope.INDIVIDUAL,
            startAt = minDate,
            endAt = minDate,
            alarmTime = Schedule.DEFAULT_ALARM_TIME
        )
    }

    private val entireScheduleStub: Schedule by lazy {
        Schedule(
            id = scheduleId,
            employeeId = userId,
            spotId = spotId,
            title = "test title",
            scope = Scope.ENTIRE,
            startAt = maxDate,
            endAt = maxDate,
            alarmTime = Schedule.DEFAULT_ALARM_TIME
        )
    }

    private val responseStub by lazy {
        QueryIndividualSpotScheduleResponse(
            listOf(
                ScheduleResponse(
                    id = scheduleId,
                    startAt = minDate,
                    endAt = minDate,
                    title = "test title",
                    scope = Scope.INDIVIDUAL
                ),
                ScheduleResponse(
                    id = scheduleId,
                    startAt = maxDate,
                    endAt = maxDate,
                    title = "test title",
                    Scope.ENTIRE
                )
            )
        )
    }

    @BeforeEach
    fun setUp() {
        queryIndividualSpotScheduleUseCase = QueryIndividualSpotScheduleUseCase(
            querySchedulePort, queryUserPort, securityPort
        )
    }

    @Test
    fun `개인 + 지점 일정 조회 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(querySchedulePort.querySchedulesByPeriodAndUserIdAndScope(date, date, userStub.id, Scope.INDIVIDUAL))
            .willReturn(
                listOf(individualScheduleStub)
            )

        given(querySchedulePort.querySchedulesByPeriodAndSpotIdAndScope(date, date, userStub.spotId, Scope.ENTIRE))
            .willReturn(
                listOf(entireScheduleStub)
            )

        // when
        val response = queryIndividualSpotScheduleUseCase.execute(date, date)

        // then
        assertThat(response).isEqualTo(responseStub)
    }

    @Test
    fun `유저가 존재하지 않음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            queryIndividualSpotScheduleUseCase.execute(date, date)
        }
    }

}