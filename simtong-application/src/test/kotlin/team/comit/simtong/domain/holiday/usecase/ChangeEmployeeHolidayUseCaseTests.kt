package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.dto.request.ChangeEmployeeHolidayData
import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayStatus
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.CommandHolidayPort
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class ChangeEmployeeHolidayUseCaseTests {

    @MockBean
    private lateinit var queryHolidayPort: QueryHolidayPort

    @MockBean
    private lateinit var commandHolidayPort: CommandHolidayPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    @MockBean
    private lateinit var queryUserPort: HolidayQueryUserPort

    private lateinit var changeEmployeeHolidayUseCase: ChangeEmployeeHolidayUseCase

    private val beforeDate = LocalDate.now()
    private val afterDate = LocalDate.now().minusDays(1)
    private val userId = UUID.randomUUID()
    private val spotId = UUID.randomUUID()

    private val requestStub = ChangeEmployeeHolidayData(
        beforeDate = beforeDate,
        userId = userId,
        afterDate = afterDate
    )

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

    private val holidayStub: Holiday by lazy {
        Holiday(
            date = beforeDate,
            employeeId = userId,
            type = HolidayType.HOLIDAY,
            spotId = spotId,
            status = HolidayStatus.COMPLETED
        )
    }

    @BeforeEach
    fun setUp() {
        changeEmployeeHolidayUseCase = ChangeEmployeeHolidayUseCase(
            queryHolidayPort, commandHolidayPort, securityPort, queryUserPort
        )
    }

    @Test
    fun `직원 휴무일 변경 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(queryHolidayPort.queryHolidayByDateAndUserId(beforeDate, userId))
            .willReturn(holidayStub)

        given(queryHolidayPort.countHolidayByWeekAndUserIdAndType(afterDate, userId, HolidayType.HOLIDAY))
            .willReturn(1)

        // when & then
        assertDoesNotThrow {
            changeEmployeeHolidayUseCase.execute(requestStub)
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
            changeEmployeeHolidayUseCase.execute(requestStub)
        }
    }

    @Test
    fun `휴무일을 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(queryHolidayPort.queryHolidayByDateAndUserId(beforeDate, userId))
            .willReturn(null)

        // when & then
        assertThrows<HolidayExceptions.NotFound> {
            changeEmployeeHolidayUseCase.execute(requestStub)
        }
    }

    @Test
    fun `다른 지점 직원의 휴무일을 변경할 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(queryHolidayPort.queryHolidayByDateAndUserId(beforeDate, userId))
            .willReturn(
                holidayStub.copy(spotId = UUID.randomUUID())
            )

        // when & then
        assertThrows<HolidayExceptions.CannotChange> {
            changeEmployeeHolidayUseCase.execute(requestStub)
        }
    }

    @Test
    fun `주 휴무일 한도 초과`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryUserPort.queryUserById(userId))
            .willReturn(userStub)

        given(queryHolidayPort.queryHolidayByDateAndUserId(beforeDate, userId))
            .willReturn(holidayStub)

        given(queryHolidayPort.countHolidayByWeekAndUserIdAndType(afterDate, userId, HolidayType.HOLIDAY))
            .willReturn(123456)

        // when & then
        assertThrows<HolidayExceptions.WeekHolidayLimitExcess> {
            changeEmployeeHolidayUseCase.execute(requestStub)
        }
    }
}