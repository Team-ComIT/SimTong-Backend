package team.comit.simtong.domain.spot.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.spot.dto.SpotResponse
import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.domain.spot.spi.QuerySpotPort
import java.util.UUID

@ExtendWith(SpringExtension::class)
class ShowSpotListUseCaseTests {

    @MockBean
    private lateinit var querySpotPort: QuerySpotPort

    private lateinit var showSpotListUseCase: ShowSpotListUseCase

    private val id = UUID.randomUUID()

    private val name = "test name";

    private val location = "test location"

    private val spotListStub: List<Spot> by lazy {
        listOf(
            Spot(
                id = id,
                name = name,
                location = location
            )
        )
    }

    private val responseStub: SpotResponse by lazy {
        SpotResponse(
            listOf(
                SpotResponse.SpotElement(
                    id = id,
                    name = name,
                    location = location
                )
            )
        )
    }

    @BeforeEach
    fun setUp() {
        showSpotListUseCase = ShowSpotListUseCase(querySpotPort)
    }

    @Test
    fun `지점 리스트 반환 성공`() {
        // given
        given(querySpotPort.queryAllSpot())
            .willReturn(spotListStub)

        // when & then
        assertEquals(showSpotListUseCase.execute(), responseStub)
    }

}