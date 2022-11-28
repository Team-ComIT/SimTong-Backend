package team.comit.simtong.domain.menu.usecase

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.spi.QueryMenuPort
import team.comit.simtong.global.annotation.SimtongTest
import java.time.LocalDate
import java.util.UUID

@SimtongTest
class QueryPublicMenuUseCaseTests {

    @MockBean
    private lateinit var queryMenuPort: QueryMenuPort

    private lateinit var queryPublicMenuUseCase: QueryPublicMenuUseCase

    private val menuStub: Menu by lazy {
        Menu(
            date = LocalDate.of(2022, 9, 1),
            meal = "오늘 아침은 아침밥",
            spotId = UUID.randomUUID()
        )
    }

    private val menuStub2: Menu by lazy {
        Menu(
            date = LocalDate.of(2022, 9, 30),
            meal = "오늘 점심은 점심밥",
            spotId = UUID.randomUUID()
        )
    }

    @BeforeEach
    fun setUp() {
        queryPublicMenuUseCase = QueryPublicMenuUseCase(queryMenuPort)
    }

    @Test
    fun `메뉴 조회 성공`() {
        // given
        val now = LocalDate.now()
        given(queryMenuPort.queryMenuBySpotName(now.year, now.monthValue, "은행동 본점"))
            .willReturn(
                listOf(menuStub, menuStub2)
            )

        // when
        val response = queryPublicMenuUseCase.execute(now)

        // then
        Assertions.assertThat(response).isNotNull
    }

}