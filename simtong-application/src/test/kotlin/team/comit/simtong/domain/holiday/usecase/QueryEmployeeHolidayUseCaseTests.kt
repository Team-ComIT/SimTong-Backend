package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.model.HolidayQueryType
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.domain.holiday.spi.vo.EmployeeHoliday
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class QueryEmployeeHolidayUseCaseTests {

    @MockBean
    private lateinit var queryHolidayPort: QueryHolidayPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    @MockBean
    private lateinit var queryUserPort: HolidayQueryUserPort

    private lateinit var queryEmployeeHolidayUseCase: QueryEmployeeHolidayUseCase

    private val userId = UUID.randomUUID()
    private val spotId = UUID.randomUUID()
    private val year = 2022
    private val month = 12
    private val teamId = UUID.randomUUID()

    private val userStub by lazy {
        User.of(
            id = userId,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = spotId,
            teamId = UUID.randomUUID(),
            profileImagePath = User.DEFAULT_IMAGE
        )
    }

    @BeforeEach
    fun setUp() {
        queryEmployeeHolidayUseCase = QueryEmployeeHolidayUseCase(
            queryHolidayPort, securityPort, queryUserPort
        )
    }

    @Test
    fun `지점 직원 휴무일 조회 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(queryHolidayPort.queryEmployeeHolidaysByYearAndMonthAndTeamId(year, month, HolidayType.HOLIDAY, spotId, teamId))
            .willReturn(
                listOf(
                    EmployeeHoliday(
                        date = LocalDate.of(year, month, 1),
                        userId = userId,
                        type = HolidayType.HOLIDAY,
                        userName = "test name",
                        teamName = "test team",
                        spotName = "test spot",
                    )
                )
            )

        // when & then
        assertDoesNotThrow {
            queryEmployeeHolidayUseCase.execute(year, month, HolidayQueryType.HOLIDAY, teamId)
        }
    }

    @Test
    fun `지점 직원 연차 조회 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(queryHolidayPort.queryEmployeeHolidaysByYearAndMonthAndTeamId(year, month, HolidayType.ANNUAL, spotId, teamId))
            .willReturn(
                listOf(
                    EmployeeHoliday(
                        date = LocalDate.of(year, month, 1),
                        userId = userId,
                        type = HolidayType.ANNUAL,
                        userName = "test name",
                        teamName = "test team",
                        spotName = "test spot",
                    )
                )
            )

        // when & then
        assertDoesNotThrow {
            queryEmployeeHolidayUseCase.execute(year, month, HolidayQueryType.ANNUAL, teamId)
        }
    }

    @Test
    fun `지점 직원 휴무일 & 연차 조회 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(queryHolidayPort.queryEmployeeHolidaysByYearAndMonthAndTeamId(year, month, null, spotId, teamId))
            .willReturn(
                listOf(
                    EmployeeHoliday(
                        date = LocalDate.of(year, month, 1),
                        userId = userId,
                        type = HolidayType.ANNUAL,
                        userName = "test name",
                        teamName = "test team",
                        spotName = "test spot",
                    )
                )
            )

        // when & then
        assertDoesNotThrow {
            queryEmployeeHolidayUseCase.execute(year, month, HolidayQueryType.ALL, teamId)
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
            queryEmployeeHolidayUseCase.execute(2022, 12, HolidayQueryType.HOLIDAY, null)
        }
    }
}