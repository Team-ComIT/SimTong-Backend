package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.global.annotation.SimtongTest
import java.util.UUID

@SimtongTest
class QueryRestOfAnnualUseCaseTests {

    @MockBean
    private lateinit var queryHolidayPort: QueryHolidayPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    private lateinit var queryRestOfAnnualUseCase: QueryRestOfAnnualUseCase

    private val id: UUID = UUID.randomUUID()

    private val year: Int = 2023

    @BeforeEach
    fun setUp() {
        queryRestOfAnnualUseCase = QueryRestOfAnnualUseCase(queryHolidayPort, securityPort)
    }

    @Test
    fun `남은 연차 개수`() {
        // given
        val count: Long = 0

        given(securityPort.getCurrentUserId())
            .willReturn(id)

        given(queryHolidayPort.countHolidayByYearAndUserIdAndType(year, id, HolidayType.ANNUAL))
            .willReturn(count)

        // when
        val result = queryRestOfAnnualUseCase.execute(year)

        // then
        assertEquals(result.result, Holiday.ANNUAL_LEAVE_LIMIT - count)
    }
}