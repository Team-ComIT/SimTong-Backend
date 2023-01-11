package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.dto.response.QueryMonthHolidayPeriodResponse
import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.model.HolidayPeriod
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPeriodPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.model.value.Authority
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class QueryMonthHolidayPeriodUseCaseTests {

    @MockBean
    private lateinit var queryHolidayPeriodPort: QueryHolidayPeriodPort

    @MockBean
    private lateinit var queryUserPort: HolidayQueryUserPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    private lateinit var queryMonthHolidayPeriodUseCase: QueryMonthHolidayPeriodUseCase

    private val id: UUID = UUID.randomUUID()

    private val spotId: UUID = UUID.randomUUID()

    private val year: Int = 2022

    private val month: Int = 12

    private val startAt: LocalDate = LocalDate.now()

    private val endAt: LocalDate = LocalDate.now()

    private val userStub: User by lazy {
        User(
            id = id,
            nickname = "test nickname",
            email = "test@test.com",
            name = "test name",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_ADMIN,
            spotId = spotId,
            teamId = id,
            profileImagePath = User.DEFAULT_IMAGE
        )
    }

    private val holidayPeriodStub: HolidayPeriod by lazy {
        HolidayPeriod(
            year = year,
            month = month,
            spotId = spotId,
            startAt = startAt,
            endAt = endAt
        )
    }

    private val responseStub: QueryMonthHolidayPeriodResponse by lazy {
        QueryMonthHolidayPeriodResponse(
            startAt = startAt,
            endAt = endAt
        )
    }

    @BeforeEach
    fun setUp() {
        queryMonthHolidayPeriodUseCase = QueryMonthHolidayPeriodUseCase(
            queryHolidayPeriodPort = queryHolidayPeriodPort,
            queryUserPort = queryUserPort,
            securityPort = securityPort
        )
    }

    @Test
    fun `휴무표 작성 기간 조회 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(queryHolidayPeriodPort.queryHolidayPeriodByYearAndMonthAndSpotId(year, month, spotId))
            .willReturn(holidayPeriodStub)

        // when
        val response = queryMonthHolidayPeriodUseCase.execute(year, month)

        // then
        assertEquals(response, responseStub)
    }

    @Test
    fun `등록되지 않은 휴무표 작성 기간`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(queryHolidayPeriodPort.queryHolidayPeriodByYearAndMonthAndSpotId(year, month, spotId))
            .willReturn(null)

        // when & then
        assertThrows<HolidayExceptions.NotFound> {
            queryMonthHolidayPeriodUseCase.execute(year, month)
        }
    }

    @Test
    fun `유저를 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            queryMonthHolidayPeriodUseCase.execute(year, month)
        }
    }

}