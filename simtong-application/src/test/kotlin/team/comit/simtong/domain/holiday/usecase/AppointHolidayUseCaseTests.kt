package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.model.Holiday
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
class AppointHolidayUseCaseTests {

    @MockBean
    private lateinit var commandHolidayPort: CommandHolidayPort

    @MockBean
    private lateinit var queryHolidayPort: QueryHolidayPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    @MockBean
    private lateinit var queryUserPort: HolidayQueryUserPort

    private lateinit var appointHolidayUseCase: AppointHolidayUseCase

    private val id = UUID.randomUUID()

    private val userStub: User by lazy {
        User(
            id = id,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = id,
            teamId = id,
            profileImagePath = User.DEFAULT_IMAGE
        )
    }

    private val dateStub: LocalDate = LocalDate.now()

    @BeforeEach
    fun setUp() {
        appointHolidayUseCase = AppointHolidayUseCase(
            commandHolidayPort = commandHolidayPort,
            queryHolidayPort = queryHolidayPort,
            securityPort = securityPort,
            queryUserPort = queryUserPort
        )
    }

    @Test
    fun `휴무일 지정 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(queryHolidayPort.countHolidayByWeekAndUserIdAndType(dateStub, id, HolidayType.HOLIDAY))
            .willReturn(0)

        // when & then
        assertDoesNotThrow {
            appointHolidayUseCase.execute(dateStub)
        }
    }

    @Test
    fun `주 휴무일 한도 초과`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(queryHolidayPort.countHolidayByWeekAndUserIdAndType(dateStub, id, HolidayType.HOLIDAY))
            .willReturn(Holiday.WEEK_HOLIDAY_LIMIT)

        // when & then
        assertThrows<HolidayExceptions.WeekHolidayLimitExcess> {
            appointHolidayUseCase.execute(dateStub)
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
            appointHolidayUseCase.execute(dateStub)
        }
    }

}