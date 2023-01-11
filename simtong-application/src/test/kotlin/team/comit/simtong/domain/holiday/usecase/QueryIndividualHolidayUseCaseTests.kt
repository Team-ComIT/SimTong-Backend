package team.comit.simtong.domain.holiday.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.holiday.dto.QueryIndividualRequest
import team.comit.simtong.domain.holiday.model.Holiday
import team.comit.simtong.domain.holiday.model.value.HolidayStatus
import team.comit.simtong.domain.holiday.model.value.HolidayType
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

    private val requestStub: QueryIndividualRequest by lazy {
        QueryIndividualRequest(
            startAt = LocalDate.now(),
            endAt = LocalDate.now(),
            status = HolidayStatus.COMPLETED
        )
    }

    private val holidaysStub: List<Holiday> by lazy {
        listOf(
            Holiday(
                date = LocalDate.now(),
                employeeId = userId,
                type = HolidayType.HOLIDAY,
                spotId = UUID.randomUUID(),
                status = HolidayStatus.COMPLETED
            )
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

        given(queryHolidayPort.queryHolidaysByPeriodAndUserIdAndStatus(requestStub.startAt, requestStub.endAt, userId, HolidayStatus.COMPLETED))
            .willReturn(holidaysStub)

        // when
        val result = queryIndividualHolidayUseCase.execute(requestStub)

        // then
        assertEquals(result, holidaysStub)
    }

}