package team.comit.simtong.domain.schedule.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.DomainPropertiesInitialization
import team.comit.simtong.domain.schedule.dto.EntireSpotScheduleResponse
import team.comit.simtong.domain.schedule.dto.SpotScheduleResponse
import team.comit.simtong.domain.schedule.model.Scope
import team.comit.simtong.domain.schedule.spi.QuerySchedulePort
import team.comit.simtong.domain.schedule.vo.SpotSchedule
import java.time.LocalDate
import java.util.UUID

@Import(DomainPropertiesInitialization::class)
@ExtendWith(SpringExtension::class)
class EntireSpotScheduleUseCaseTests {

    @MockBean
    private lateinit var querySchedulePort: QuerySchedulePort

    private lateinit var entireSpotScheduleUseCase: EntireSpotScheduleUseCase

    private val date: LocalDate = LocalDate.now()

    private val uuid: UUID = UUID.randomUUID()

    private val spotScheduleListStub = listOf(
        SpotSchedule(
            id = uuid,
            spotId = uuid,
            spotName = "test name",
            title = "test title",
            startAt = date,
            endAt = date
        )
    )

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
        entireSpotScheduleUseCase = EntireSpotScheduleUseCase(querySchedulePort)
    }

    @Test
    fun `전체 지점 일정 조회 성공`() {
        // given
        given(querySchedulePort.querySchedulesByMonthAndScope(date, Scope.ENTIRE))
            .willReturn(spotScheduleListStub)

        // when
        val response = entireSpotScheduleUseCase.execute(date)

        // then
        assertEquals(response, responseStub)
    }

}