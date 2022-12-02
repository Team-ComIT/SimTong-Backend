package team.comit.simtong.domain.schedule.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.schedule.dto.QueryIndividualSpotScheduleResponse
import team.comit.simtong.domain.schedule.dto.ScheduleResponse
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQueryUserPort
import team.comit.simtong.domain.schedule.spi.ScheduleSecurityPort
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.*

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

    private val scheduleStub: Schedule by lazy {
        Schedule(
            id = scheduleId,
            userId = userId,
            spotId = spotId,
            title = "test title",
            scope = Scope.INDIVIDUAL,
            startAt = date,
            endAt = date,
            alarmTime = Schedule.DEFAULT_ALARM_TIME
        )
    }

    private val responseStub by lazy {
        QueryIndividualSpotScheduleResponse(
            listOf(
                ScheduleResponse(
                    id = scheduleId,
                    startAt = date,
                    endAt = date,
                    title = "test title"
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

        given(querySchedulePort.querySchedulesByMonthAndUserIdAndScope(date, userStub.id, Scope.INDIVIDUAL))
            .willReturn(
                listOf(scheduleStub)
            )

        given(querySchedulePort.querySchedulesByMonthAndSpotIdAndScope(date, userStub.spotId, Scope.INDIVIDUAL))
            .willReturn(
                listOf(scheduleStub)
            )

        // when
        val response = queryIndividualSpotScheduleUseCase.execute(date)

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
        assertThrows<UserNotFoundException> {
            queryIndividualSpotScheduleUseCase.execute(date)
        }
    }

}