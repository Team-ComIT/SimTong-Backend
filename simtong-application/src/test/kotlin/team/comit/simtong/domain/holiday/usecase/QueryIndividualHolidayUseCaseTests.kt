package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.dto.IndividualHolidayResponse
import team.comit.simtong.domain.holiday.dto.QueryIndividualHolidaysResponse
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.domain.holiday.spi.HolidaySecurityPort
import team.comit.simtong.domain.holiday.spi.QueryHolidayPort
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class QueryIndividualHolidayUseCaseTests {

    @MockBean
    private lateinit var queryHolidayPort: QueryHolidayPort

    @MockBean
    private lateinit var securityPort: HolidaySecurityPort

    private lateinit var queryIndividualHolidayUseCase: QueryIndividualHolidayUseCase

    private val userId: UUID = UUID.randomUUID()

    private val date: LocalDate = LocalDate.now()

    private val holidaysStub: List<Holiday> by lazy {
        listOf(
            Holiday(
                date = LocalDate.now(),
                userId = userId,
                type = HolidayType.HOLIDAY,
                spotId = UUID.randomUUID()
            )
        )
    }

    private val responseStub : QueryIndividualHolidaysResponse by lazy {
        QueryIndividualHolidaysResponse(
            holidaysStub.map {
                IndividualHolidayResponse(
                    date = it.date,
                    type = it.type.name
                )
            }
        )
    }

    @BeforeEach
    fun setUp() {
        queryIndividualHolidayUseCase = QueryIndividualHolidayUseCase(
            queryHolidayPort = queryHolidayPort,
            securityPort = securityPort
        )
    }

    @Test
    fun `개인 휴무일 조회 성공`() {
        // given
        given(securityPort.getCurrentUserId())
            .willReturn(userId)

        given(queryHolidayPort.queryHolidaysByPeriodAndUserId(date, date, userId))
            .willReturn(holidaysStub)

        // when
        val response = queryIndividualHolidayUseCase.execute(date, date)

        // then
        assertEquals(response, responseStub)
    }

}