package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.dto.request.CancelHolidayData
import team.comit.simtong.domain.holiday.exception.HolidayExceptions
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayStatus
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.CommandHolidayPort
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class CancelHolidayUseCaseTests {

    @MockBean
    private lateinit var commandHolidayPort: CommandHolidayPort

    @MockBean
    private lateinit var queryHolidayPort: QueryHolidayPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    private lateinit var cancelHolidayUseCase: CancelHolidayUseCase

    private val id: UUID = UUID.randomUUID()

    private val date: LocalDate = LocalDate.now()

    private val requestStub: CancelHolidayData = CancelHolidayData(date)

    @BeforeEach
    fun setUp() {
        cancelHolidayUseCase = CancelHolidayUseCase(
            commandHolidayPort = commandHolidayPort,
            queryHolidayPort = queryHolidayPort,
            securityPort = securityPort
        )
    }

    @Test
    fun `작성중인 휴무일 취소`() {
        // given
        val holidayStub = Holiday(
            date = date,
            employeeId = id,
            type = HolidayType.HOLIDAY,
            spotId = id,
            status = HolidayStatus.WRITTEN
        )

        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryHolidayPort.queryHolidayByDateAndUserId(date, id))
            .willReturn(holidayStub)

        // when & then
        assertDoesNotThrow {
            cancelHolidayUseCase.execute(requestStub)
        }
    }

    @Test
    fun `확정된 휴무일 취소`() {
        // given
        val holidayStub = Holiday(
            date = date,
            employeeId = id,
            type = HolidayType.HOLIDAY,
            spotId = id,
            status = HolidayStatus.COMPLETED
        )

        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryHolidayPort.queryHolidayByDateAndUserId(date, id))
            .willReturn(holidayStub)

        // when & then
        assertThrows<HolidayExceptions.CannotChange> {
            cancelHolidayUseCase.execute(requestStub)
        }
    }

    @Test
    fun `미래의 연차 취소`() {
        // given
        val holidayStub = Holiday(
            date = LocalDate.MAX,
            employeeId = id,
            type = HolidayType.ANNUAL,
            spotId = id,
            status = HolidayStatus.COMPLETED
        )

        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryHolidayPort.queryHolidayByDateAndUserId(date, id))
            .willReturn(holidayStub)

        // when & then
        assertDoesNotThrow {
            cancelHolidayUseCase.execute(requestStub)
        }
    }

    @Test
    fun `과거의 연차 취소`() {
        // given
        val holidayStub = Holiday(
            date = LocalDate.MIN,
            employeeId = id,
            type = HolidayType.ANNUAL,
            spotId = id,
            status = HolidayStatus.COMPLETED
        )

        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryHolidayPort.queryHolidayByDateAndUserId(date, id))
            .willReturn(holidayStub)

        // when & then
        assertThrows<HolidayExceptions.CannotChange> {
            cancelHolidayUseCase.execute(requestStub)
        }
    }

    @Test
    fun `휴무일을 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryHolidayPort.queryHolidayByDateAndUserId(date, id))
            .willReturn(null)

        // when & then
        assertThrows<HolidayExceptions.NotFound> {
            cancelHolidayUseCase.execute(requestStub)
        }
    }

}