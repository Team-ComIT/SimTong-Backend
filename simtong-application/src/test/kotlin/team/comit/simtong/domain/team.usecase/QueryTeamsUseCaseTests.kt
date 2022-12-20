package team.comit.simtong.domain.team.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.team.dto.QueryTeamsResponse
import team.comit.simtong.domain.team.model.Team
import team.comit.simtong.domain.team.spi.QueryTeamPort
import team.comit.simtong.global.annotation.SimtongTest
import java.util.UUID

@SimtongTest
class QueryTeamsUseCaseTests {

    @MockBean
    private lateinit var queryTeamPort: QueryTeamPort

    private lateinit var queryTeamsUseCase: QueryTeamsUseCase

    private val id = UUID.randomUUID()
    private val spotId = UUID.randomUUID()
    private val name = "test name"

    private val teamsStub: List<Team> by lazy {
        listOf(
            Team(
                id = id,
                name = name,
                spotId = spotId
            )
        )
    }

    private val responseStub: QueryTeamsResponse by lazy {
        QueryTeamsResponse(
            listOf(
                QueryTeamsResponse.TeamElement(
                    id = id,
                    name = name
                )
            )
        )
    }

    @BeforeEach
    fun setUp() {
        queryTeamsUseCase = QueryTeamsUseCase(queryTeamPort)
    }

    @Test
    fun `지점 리스트 반환 성공`() {
        // given
        given(queryTeamPort.queryTeamsBySpotId(spotId))
            .willReturn(teamsStub)

        // when & then
        assertEquals(queryTeamsUseCase.execute(spotId), responseStub)
    }

}