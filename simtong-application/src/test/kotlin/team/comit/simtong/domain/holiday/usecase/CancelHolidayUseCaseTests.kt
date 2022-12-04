package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.exception.HolidayNotFoundException
import team.comit.simtong.domain.holiday.model.Holiday
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

    private val dateStub: LocalDate = LocalDate.now()

    private val holidayStub: Holiday by lazy {
        Holiday(
            date = dateStub,
            userId = id,
            type = HolidayType.HOLIDAY,
            spotId = id
        )
    }

    @BeforeEach
    fun setUp() {
        cancelHolidayUseCase = CancelHolidayUseCase(
            commandHolidayPort = commandHolidayPort,
            queryHolidayPort = queryHolidayPort,
            securityPort = securityPort
        )
    }

    @Test
    fun `휴무일 취소 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryHolidayPort.queryHolidayByDateAndUserId(dateStub, id))
            .willReturn(holidayStub)

        // when & then
        assertDoesNotThrow {
            cancelHolidayUseCase.execute(dateStub)
        }
    }

    @Test
    fun `휴무일을 찾을 수 없음`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryHolidayPort.queryHolidayByDateAndUserId(dateStub, id))
            .willReturn(null)

        // when & then
        assertThrows<HolidayNotFoundException> {
            cancelHolidayUseCase.execute(dateStub)
        }
    }

}