package team.comit.simtong.domain.schedule.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.schedule.dto.EntireSpotScheduleResponse
import team.comit.simtong.domain.schedule.dto.SpotScheduleResponse
import team.comit.simtong.domain.schedule.model.Schedule
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.spi.ScheduleQuerySpotPort
import team.comit.simtong.domain.spot.exception.SpotNotFoundException
import team.comit.simtong.domain.spot.model.Spot
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@ExtendWith(SpringExtension::class)
class EntireSpotScheduleUseCaseTests {

    @MockBean
    private lateinit var querySchedulePort: QuerySchedulePort

    @MockBean
    private lateinit var querySpotPort: ScheduleQuerySpotPort

    private lateinit var entireSpotScheduleUseCase: EntireSpotScheduleUseCase

    private val date: LocalDate = LocalDate.now()

    private val uuid: UUID = UUID.randomUUID()

    private val scheduleListStub = listOf(
        Schedule(
            id = uuid,
            userId = uuid,
            spotId = uuid,
            title = "test title",
            scope = Scope.ENTIRE,
            startAt = date,
            endAt = date,
            alarmTime = LocalTime.now()
        )
    )

    private val spotStub: Spot by lazy {
        Spot(
            id = uuid,
            name = "test name",
            location = "test location"
        )
    }

    private val responseStub: EntireSpotScheduleResponse by lazy {
        EntireSpotScheduleResponse(
            listOf(
                SpotScheduleResponse(
                    id = uuid,
                    startAt = date,
                    endAt = date,
                    title = "test title",
                    spot = SpotScheduleResponse.SpotElement(
                        id = uuid,
                        name = "test name"
                    )
                )
            )
        )
    }

    @BeforeEach
    fun setUp() {
        entireSpotScheduleUseCase = EntireSpotScheduleUseCase(querySchedulePort, querySpotPort)
    }

    @Test
    fun `전체 지점 일정 조회 성공`() {
        // given
        given(querySchedulePort.querySchedulesByMonth(date.year, date.monthValue))
            .willReturn(scheduleListStub)

        given(querySpotPort.querySpotById(uuid))
            .willReturn(spotStub)

        // when
        val response = entireSpotScheduleUseCase.execute(date)

        // then
        assertEquals(response, responseStub)
    }

    @Test
    fun `지점을 찾을 수 없음`() {
        // given
        given(querySchedulePort.querySchedulesByMonth(date.year, date.monthValue))
            .willReturn(scheduleListStub)

        given(querySpotPort.querySpotById(uuid))
            .willReturn(null)

        // when & then
        assertThrows<SpotNotFoundException> {
            entireSpotScheduleUseCase.execute(date)
        }
    }
}