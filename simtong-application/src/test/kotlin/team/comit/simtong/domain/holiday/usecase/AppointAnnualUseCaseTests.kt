package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.value.HolidayStatus
import team.comit.simtong.domain.holiday.model.value.HolidayType
import team.comit.simtong.domain.holiday.spi.CommandHolidayPort
import team.comit.simtong.domain.holiday.spi.HolidayQueryUserPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.value.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class AppointAnnualUseCaseTests {

    @MockBean
    private lateinit var queryHolidayPort: QueryHolidayPort

    @MockBean
    private lateinit var commandHolidayPort: CommandHolidayPort

    @MockBean
    private lateinit var queryUserPort: HolidayQueryUserPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    private lateinit var appointAnnualUseCase: AppointAnnualUseCase

    private val id: UUID = UUID.randomUUID()

    private val date: LocalDate = LocalDate.MAX

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

    @BeforeEach
    fun setUp() {
        appointAnnualUseCase = AppointAnnualUseCase(
            queryHolidayPort = queryHolidayPort,
            commandHolidayPort = commandHolidayPort,
            queryUserPort = queryUserPort,
            securityPort = securityPort
        )
    }

    @Test
    fun `연차 지정 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(queryHolidayPort.existsHolidayByDateAndUserIdAndType(date, userStub.id, HolidayType.ANNUAL))
            .willReturn(false)

        given(queryHolidayPort.countHolidayByYearAndUserIdAndType(date.year, id, HolidayType.ANNUAL))
            .willReturn(0)

        // when & then
        assertDoesNotThrow {
            appointAnnualUseCase.execute(date)
        }
    }

    @Test
    fun `연차 사용 개수 초과`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(queryHolidayPort.existsHolidayByDateAndUserIdAndType(date, userStub.id, HolidayType.ANNUAL))
            .willReturn(false)

        given(queryHolidayPort.countHolidayByYearAndUserIdAndType(date.year, id, HolidayType.ANNUAL))
            .willReturn(Holiday.ANNUAL_LEAVE_LIMIT)

        // when & then
        assertThrows<HolidayExceptions.AnnualLeaveLimitExcess> {
            appointAnnualUseCase.execute(date)
        }
    }

    @Test
    fun `작성중인 휴무일을 변경할 때`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(queryHolidayPort.existsHolidayByDateAndUserIdAndType(date, userStub.id, HolidayType.ANNUAL))
            .willReturn(false)

        given(queryHolidayPort.countHolidayByYearAndUserIdAndType(date.year, id, HolidayType.ANNUAL))
            .willReturn(0)

        // when & then
        assertDoesNotThrow {
            appointAnnualUseCase.execute(date)
        }
    }

    @Test
    fun `이미 연차일때`() {
        // given
        val annualStub = Holiday(
            date = date,
            spotId = id,
            type = HolidayType.ANNUAL,
            employeeId = id,
            status = HolidayStatus.COMPLETED
        )

        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(queryHolidayPort.existsHolidayByDateAndUserIdAndType(date, userStub.id, HolidayType.ANNUAL))
            .willReturn(true)

        // when & then
        assertThrows<HolidayExceptions.AlreadyExists> {
            appointAnnualUseCase.execute(date)
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
            appointAnnualUseCase.execute(date)
        }
    }

    @Test
    fun `과거에 연차를 지정할 때`() {
        // when & then
        assertThrows<HolidayExceptions.CannotChange> {
            appointAnnualUseCase.execute(LocalDate.MIN)
        }
    }
}